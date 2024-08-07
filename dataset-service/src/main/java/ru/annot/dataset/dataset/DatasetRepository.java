package ru.annot.dataset.dataset;

public interface DatasetRepository {

//    Long save(String name, String description);

    Dataset save(Dataset dataset);

    Dataset getById(Long id);

    Iterable<Dataset> getAll();

    void deleteById(Long id);

    void update(Dataset dataset);
}
