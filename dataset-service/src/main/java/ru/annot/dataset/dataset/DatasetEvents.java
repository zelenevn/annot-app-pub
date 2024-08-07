package ru.annot.dataset.dataset;

public interface DatasetEvents {

    void publishCreateEvent(Dataset dataset);

    void publishUpdateEvent(Dataset dataset);

    void publishDeleteEvent(Dataset dataset);
}
