package ru.annot.layout.classification;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;
import java.util.List;

@MongoEntity(database = "dataset", collection = "classification")
public class ClassificationDatasetEntity extends PanacheMongoEntity {

    public Long id;
    public String name;
    public String description;
    public List<String> labels = new ArrayList<>();
}
