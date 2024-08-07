package ru.annot.layout.dataset;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DatasetsUsecases implements Datasets {

    @Inject
    DatasetRepository datasetRepository;

    @Override
    public void createDataset(Long id, String name, String description) {
        datasetRepository.createDataset(id, name, description);
    }
}
