package ru.annot.dataset.file;

import java.io.File;
import java.io.InputStream;

public interface FileStorage {

    String saveFile(String filename, File file);

    InputStream getByKey(String key);
}
