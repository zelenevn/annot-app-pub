package ru.annot.layout.classification;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("dataset/classification")
public class ClassificationDatasetResource {

    @Inject
    ClassificationDatasetUsecases usecases;

    @POST
    @Path("lables")
    public void addLabel(@QueryParam("id") Long datasetId, @QueryParam("label") String label) {

    }
}
