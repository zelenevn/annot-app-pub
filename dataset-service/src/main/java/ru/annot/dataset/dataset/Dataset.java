package ru.annot.dataset.dataset;

import ru.annot.validation.DomainValidationException;
import ru.annot.validation.ValidationDomain;

import java.util.List;

public class Dataset extends ValidationDomain {

    private static final List<String> TASKS = List.of("CLASSIFICATION");

    private Long id;
    private String name;
    private String description;
    private String task;

    public Dataset(String name, String description, String task) throws DomainValidationException {
        this.name = name;
        this.description = description;
        this.task = task.toUpperCase();

        registerValidation(name::isEmpty, "Name of dataset cannot be empty");
        registerValidation(() -> !TASKS.contains(this.task), "Wrong task");

        validate(errors -> new DomainValidationException(errors, " | "));
    }

    Dataset(Long id, String name, String description, String task) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
