export type AnnotationTask = 'CLASSIFICATION' | 'SEGMENTATION';

export default class Dataset {
    id: number
    name: string;
    description: string;
    task: AnnotationTask;

    constructor(id: number, name: string, description: string, task: AnnotationTask) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.task = task;
    }
}