
type DatasetDisplayProps = {
    name: string;
    description: string;
    task: string;
};

export default function Dataset({name, description, task}: DatasetDisplayProps) {

    return (
        <div className="dataset-display p-4 border border-gray-300 rounded-md shadow">
            <h2 className="text-lg font-semibold">{name}</h2>
            <p>{description}</p>
            <p className="text-sm text-gray-600">Task: {task}</p>
        </div>
    );
}