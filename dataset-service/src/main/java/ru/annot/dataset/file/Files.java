package ru.annot.dataset.file;

import java.io.File;
import java.io.InputStream;

public interface Files {

    void uploadFile(Long datasetId, String filename, File file);

    InputStream getByKey(String key);
}
