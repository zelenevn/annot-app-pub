package ru.annot.dataset.task;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class TasksUsecases implements Tasks {

    private final TasksRepository repository;

    @Override
    public Iterable<Task> getAll() {
        return repository.getAll();
    }
}
