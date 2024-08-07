package ru.annot.dataset.dataset;

public class DatasetDto {

    private Long id;
    private String name;
    private String description;
    private String task;

    public DatasetDto() {
    }

    public DatasetDto(Long id, String name, String description, String task) {
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
