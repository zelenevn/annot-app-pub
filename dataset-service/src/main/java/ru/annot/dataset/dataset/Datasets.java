package ru.annot.dataset.dataset;

import java.io.File;

public interface Datasets {

    Dataset create(String name, String description, String task);

    Dataset getById(Long datasetId);

    Iterable<Dataset> getAll();

    void uploadFile(Long datasetId, String filename, File file);

    void deleteDataset(Long datasetId);

    void update(Dataset dataset);
}
