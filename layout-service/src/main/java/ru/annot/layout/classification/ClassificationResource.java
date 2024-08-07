package ru.annot.layout.classification;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;

@Path("classification")
public class ClassificationResource {

    @Inject
    ClassificationDatasetUsecases usecases;

    @POST
    @Path("lable")
    public void addLable(@QueryParam("id") Long datasetId, @QueryParam("label") String label) {
        usecases.addLable(datasetId, label);
    }

    @GET
    @Path("lable")
    public List<String> getLablesByDatasetId(@QueryParam("id") Long datasetId) {
        return usecases.getLablesByDatasetId(datasetId);
    }
}
