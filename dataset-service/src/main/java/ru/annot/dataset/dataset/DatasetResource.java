package ru.annot.dataset.dataset;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import ru.annot.dataset.file.UploadedFile;

import java.io.File;

@Path("dataset")
public class DatasetResource {

    private final Datasets datasets;

    public DatasetResource(Datasets datasets) {
        this.datasets = datasets;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Dataset getById(@QueryParam("id") Long id) {
        return datasets.getById(id);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Dataset> getAll() {
        return datasets.getAll();
    }

    @POST
    public Dataset create(
            @QueryParam("name") String name,
            @QueryParam("description") String description,
            @QueryParam("task") String task
    ) {
        return datasets.create(name, description, task);
    }

    @PUT
    public void update(Dataset dataset) {
        datasets.update(dataset);
    }

    @DELETE
    public void deleteById(@QueryParam("id") Long id) {
        datasets.deleteDataset(id);
    }

    @POST
    @Path("upload")
    public void uploadFile(@QueryParam("id") Long datasetId, @RestForm("file") FileUpload multipartFile) {
        String filename = multipartFile.fileName();
        File uploadedFile = multipartFile.uploadedFile().toFile();
        datasets.uploadFile(datasetId, filename, uploadedFile);
    }

    @GET
    @Path("uploaded/all")
    public Iterable<UploadedFile> getAllUploadedFiles(@QueryParam("id") Long datasetId) {
        return null;
    }
}
