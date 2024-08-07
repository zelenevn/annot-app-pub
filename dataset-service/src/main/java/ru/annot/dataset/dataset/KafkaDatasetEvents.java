package ru.annot.dataset.dataset;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import static ru.annot.dataset.dataset.DatasetAction.CREATE;
import static ru.annot.dataset.dataset.DatasetAction.DELETE;
import static ru.annot.dataset.dataset.DatasetAction.UPDATE;

@ApplicationScoped
public class KafkaDatasetEvents implements DatasetEvents {

    @Inject
    @Channel("datasets")
    Emitter<DatasetEvent> datasetEmitter;

    @Override
    public void publishCreateEvent(Dataset dataset) {
        DatasetEvent event = new DatasetEvent(dataset, CREATE);
        sendDatasetEvent(event);
    }

    @Override
    public void publishUpdateEvent(Dataset dataset) {
        DatasetEvent event = new DatasetEvent(dataset, UPDATE);
        sendDatasetEvent(event);
    }

    @Override
    public void publishDeleteEvent(Dataset dataset) {
        DatasetEvent event = new DatasetEvent(dataset, DELETE);
        sendDatasetEvent(event);
    }

    private void sendDatasetEvent(DatasetEvent event) {
        datasetEmitter.send(Message.of(event));
    }
}
