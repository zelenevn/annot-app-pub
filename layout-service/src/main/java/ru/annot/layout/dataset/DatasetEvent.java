package ru.annot.layout.dataset;

public class DatasetEvent {

    private Dataset dataset;
    private DatasetAction action;

    public DatasetEvent(Dataset dataset, DatasetAction action) {
        this.dataset = dataset;
        this.action = action;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public DatasetAction getAction() {
        return action;
    }

    public void setAction(DatasetAction action) {
        this.action = action;
    }
}
