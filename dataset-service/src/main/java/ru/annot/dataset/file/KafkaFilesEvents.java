package ru.annot.dataset.file;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaFilesEvents implements FileEvents {

    @Inject
    @Channel("files")
    Emitter<UploadedFile> fileEmitter;

    @Override
    public void publishUploadEvent(UploadedFile uploadedFile) {
        fileEmitter.send(uploadedFile);
    }
}
