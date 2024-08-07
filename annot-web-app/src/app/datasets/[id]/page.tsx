'use client'

import React, { useState } from 'react';
import DatasetsHttpClient from "@/services/datasets/DatasetsHttpClient";

export default function DatasetById() {
    const [file, setFile] = useState<File | null>(null);
    const [datasetId, setDatasetId] = useState<number>(26);
    const [uploadStatus, setUploadStatus] = useState<string>('');

    // Обработчик изменения файла
    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null;
        setFile(file);
    };

    // Обработчик нажатия кнопки загрузки
    const handleUpload = async () => {
        if (file && datasetId) {
            setUploadStatus('Загрузка...');
            try {
                await DatasetsHttpClient.uploadFile(file, datasetId);
                setUploadStatus('Файл загружен!');
            } catch (error: unknown) { // Указываем, что error имеет тип unknown
                if (error instanceof Error) { // Проверяем, является ли error экземпляром класса Error
                    setUploadStatus(`Ошибка загрузки: ${error.message}`); // Теперь можно безопасно обратиться к message
                } else {
                    setUploadStatus("Upload failed: An unexpected error occurred."); // Альтернативный текст для неизвестных типов ошибок
                }
            }
        } else {
            setUploadStatus('Please select a file and ensure dataset ID is valid.');
        }
    };


    return (
        <div>
            <input type="file" onChange={handleFileChange} accept="image/*" />
            <button onClick={handleUpload}>Загрузить изображение</button>
            <div>{uploadStatus}</div>
        </div>
    );
};

