package ru.annot.layout.detection;

import ru.annot.layout.dataset.Dataset;

import java.util.ArrayList;
import java.util.List;

public class ObjectDetectionDataset extends Dataset {

    private final List<String> objects = new ArrayList<>();


    public ObjectDetectionDataset(Long id, String name, String description) {
        super(id, name, description);
    }

    public void addObject(String object) {
        this.objects.add(object);
    }
}
