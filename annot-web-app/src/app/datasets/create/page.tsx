'use client'

import {useRouter} from "next/navigation";
import React, {FormEvent, useState, ChangeEvent} from "react";
import DatasetsHttpClient from "@/services/datasets/DatasetsHttpClient";
import {AnnotationTask} from "@/services/datasets/Dataset";

export default function CreateDataset() {

    const router = useRouter()
    const [name, setName] = useState<string>("")
    const [description, setDescription] = useState<string>("")
    const [task, setTask] = useState<AnnotationTask>('CLASSIFICATION')

    async function onSubmit(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault()
        const createdDataset = await DatasetsHttpClient.create(name, description, task)
        router.push(`/datasets/${createdDataset.id}`)
    }

    function handleNameChange(e: ChangeEvent<HTMLInputElement>) {
        const name: string = e.target.value
        // console.log(name)
        setName(name);
    }

    function handleDescriptionChange(e: ChangeEvent<HTMLInputElement>) {
        const description: string = e.target.value
        setDescription(description)
    }

    function handleTaskChange(e: ChangeEvent<HTMLSelectElement>) {
        const task = e.target.value as AnnotationTask
        setTask(task)
    }

    return (
        <div className='flex flex-col items-center'>
            <form className='flex flex-col gap-2' onSubmit={(event: FormEvent<HTMLFormElement>) => {
                onSubmit(event)
            }}>
                <input className='text-black mb-2 w-72' type='text' placeholder='Название' value={name}
                       onChange={handleNameChange}/>
                <input className='text-black mb-2 w-72' type='text' placeholder='Описание' value={description}
                       onChange={handleDescriptionChange}/>
                <select id="taskSelect" className='text-black mb-2 w-72' value={task} onChange={handleTaskChange}>
                    <option value="CLASSIFICATION">CLASSIFICATION</option>
                    <option value="SEGMENTATION">SEGMENTATION</option>
                </select>
                <input className='mt-2 w-72' type='submit' value='Создать набор данных'/>
            </form>
        </div>
    );
}