package ru.annot.dataset.file;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;

@Path("files")
@RequiredArgsConstructor
public class FileResource {

    private final Files files;

    @POST
    public void uploadFile(@RestForm("datasetId") Long datasetId, @RestForm("file") FileUpload file) {
        File uploadedFile = file.uploadedFile().toFile();
        String fileName = uploadedFile.getName();
        files.uploadFile(datasetId, fileName, uploadedFile);
    }
}
