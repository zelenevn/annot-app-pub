import Dataset, {AnnotationTask} from "@/services/datasets/Dataset";

export default class DatasetsHttpClient {

    static async getById(id: number) : Promise<Dataset> {
        const response = await fetch('http://localhost:8080/dataset?' + new URLSearchParams({id: id.toString()}));

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json() as { id: number; name: string; description: string; task: AnnotationTask};
        const dataset = new Dataset(data.id, data.name, data.description, data.task);
        return dataset;
    }

    static async getAll() : Promise<Array<Dataset>> {
        const response = await fetch('http://localhost:8080/dataset/all')

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json() as Array<{id: number; name: string; description: string; task: AnnotationTask}>
        const datasets: Dataset[] = data.map(d => new Dataset(d.id, d.name, d.description, d.task));
        return datasets;
    }

    static async create(name: string, description: string, task: AnnotationTask): Promise<Dataset> {
        const response = await fetch('http://localhost:8080/dataset?' + new URLSearchParams({
            name: name,
            description: description,
            task: task
        }),
            {
                method: 'POST',
            });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json() as { id: number; name: string; description: string; task: AnnotationTask };
        const dataset = new Dataset(data.id, data.name, data.description, data.task);
        return dataset;
    }

    /**
     * Функция для загрузки изображения на сервер.
     * @param file - Файл изображения для загрузки.
     * @param datasetId - ID набора данных, к которому относится изображение.
     * @returns Promise, который резолвится с результатом запроса.
     */
    static async uploadFile(file: File, datasetId: number): Promise<Response> {
        const formData = new FormData();
        formData.append("file", file); // Добавление файла в formData под именем "file".

        const endpoint = `http://localhost:8080/dataset/upload?id=${datasetId}`;

        try {
            const response = await fetch(endpoint, {
                method: 'POST',
                body: formData,
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            return response;
        } catch (error) {
            console.error('Upload error:', error);
            throw error;
        }
    }

}