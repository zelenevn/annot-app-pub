package ru.annot.layout.dataset;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import ru.annot.layout.classification.ClassificationDatasetUsecases;

import java.util.Objects;

@ApplicationScoped
public class KafkaDatasetConsumer {


    @Inject
    ClassificationDatasetUsecases usecases;

    @Incoming("datasets")
    public void consume(DatasetEvent event) {
        Dataset dataset = event.getDataset();
        DatasetAction action = event.getAction();
        if (Objects.requireNonNull(action) == DatasetAction.CREATE) {
            usecases.createDataset(dataset.getId(), dataset.getName(), dataset.getDescription());
        }
    }
}
