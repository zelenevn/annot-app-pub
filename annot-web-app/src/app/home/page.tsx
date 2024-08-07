'use client'

import {useEffect, useState} from "react";
import Link from "next/link";
import Dataset from "@/services/datasets/Dataset";
import DatasetsHttpClient from "@/services/datasets/DatasetsHttpClient";

export default function Home() {

    const [datasets, setDatasets] = useState<Dataset[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        DatasetsHttpClient.getAll().then(data => {
            setDatasets(data);
            setLoading(false);
        }).catch(err => {
            setError(err.message);
            setLoading(false);
        });
    }, []);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h1>Наборы данных</h1>
            <Link href='/datasets/create'>
                Создать набор данных
            </Link>
            <ul>
                {datasets.map(dataset => (
                    <li key={dataset.id}>
                        <Link href={`/datasets/${dataset.id}`}>
                            <strong>{dataset.name}</strong> - {dataset.description} (Задача: {dataset.task})
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}