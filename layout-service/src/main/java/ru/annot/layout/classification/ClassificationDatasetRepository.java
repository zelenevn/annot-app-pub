package ru.annot.layout.classification;

import java.util.List;

public interface ClassificationDatasetRepository {

    void save(ClassificationDataset classificationDataset);

    ClassificationDataset findById(Long id);

    void update(ClassificationDataset classificationDataset);

    List<String> getLablesByDatasetId(Long id);
}
