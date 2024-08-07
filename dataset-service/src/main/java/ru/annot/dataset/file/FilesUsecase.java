package ru.annot.dataset.file;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.io.InputStream;

@ApplicationScoped
public class FilesUsecase implements Files {

    @Inject
    FileRepository fileRepository;

    @Inject
    FileStorage fileStorage;

    @Inject
    FileEvents events;

    @Override
    public void uploadFile(Long datasetId, String filename, File file) {
        String key = fileStorage.saveFile(filename, file);
        fileRepository.save(datasetId, key);
        events.publishUploadEvent(new UploadedFile(datasetId, key, filename));
    }

    @Override
    public InputStream getByKey(String key) {
        return fileStorage.getByKey(key);
    }
}
