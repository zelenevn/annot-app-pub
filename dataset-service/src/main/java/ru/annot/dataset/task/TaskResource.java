package ru.annot.dataset.task;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;

@Path("task")
@RequiredArgsConstructor
public class TaskResource {

    private final Tasks tasks;

    @GET
    @Path("allowed")
    public Iterable<Task> getAllowedTasks() {
        return tasks.getAll();
    }
}
