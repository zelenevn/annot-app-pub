package ru.annot.dataset.dataset;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.annot.dataset.file.Files;
import ru.annot.validation.DomainValidationException;

import java.io.File;

@ApplicationScoped
public class DatasetsUsecase implements Datasets {

    @Inject
    DatasetRepository datasetRepository;

    @Inject
    DatasetEvents events;

    @Inject
    Files files;

    @Override
    public Dataset create(String name, String description, String task) {
        Dataset dataset = null;
        try {
            dataset = new Dataset(name, description, task);
        } catch (DomainValidationException e) {
            throw new RuntimeException(e.getMessage());
        }
        dataset =  datasetRepository.save(dataset);
        events.publishCreateEvent(dataset);
        return dataset;
    }

    @Override
    public Dataset getById(Long datasetId) {
        return datasetRepository.getById(datasetId);
    }

    @Override
    public Iterable<Dataset> getAll() {
        return datasetRepository.getAll();
    }

    @Override
    public void uploadFile(Long datasetId, String filename, File file) {
        files.uploadFile(datasetId, filename, file);
    }

    @Override
    public void deleteDataset(Long datasetId) {
        datasetRepository.deleteById(datasetId);
    }

    @Override
    public void update(Dataset dataset) {
        datasetRepository.update(dataset);
        events.publishUpdateEvent(dataset);
    }
}
