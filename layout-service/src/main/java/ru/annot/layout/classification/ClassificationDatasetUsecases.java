package ru.annot.layout.classification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.annot.layout.dataset.Datasets;

import java.util.List;

@ApplicationScoped
public class ClassificationDatasetUsecases implements Datasets {

    @Inject
    ClassificationDatasetRepository repository;

    @Override
    public void createDataset(Long id, String name, String description) {
        ClassificationDataset dataset = new ClassificationDataset(id, name, description);
        repository.save(dataset);
    }

    public void update(Long id, String name, String description) {

    }

    public List<String> getLablesByDatasetId(Long id) {
        return repository.getLablesByDatasetId(id);
    }

    public void addLable(Long datasetId, String label) {
        ClassificationDataset classificationDataset = repository.findById(datasetId);
        classificationDataset.addLable(label);
        repository.update(classificationDataset);
    }
}
