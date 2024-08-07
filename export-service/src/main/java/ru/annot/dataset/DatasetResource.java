package ru.annot.dataset;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("dataset")
public class DatasetResource {

    @GET
    public Long export() {
        return null;
    }
}
