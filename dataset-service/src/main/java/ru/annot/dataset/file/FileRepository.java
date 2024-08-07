package ru.annot.dataset.file;

import java.net.URL;

public interface FileRepository {

    Long save(Long datasetId, String key);
}
