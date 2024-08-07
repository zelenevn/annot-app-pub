package ru.annot.layout.classification;

import ru.annot.layout.dataset.Dataset;

import java.util.ArrayList;
import java.util.List;

public class ClassificationDataset extends Dataset {

    private List<String> lables = new ArrayList<>();

    public ClassificationDataset(Long id, String name, String description) {
        super(id, name, description);
    }

    public ClassificationDataset(Long id, String name, String description, List<String> lables) {
        super(id, name, description);
        this.lables = lables;
    }

    public void addLable(String lable) {
        this.lables.add(lable);
    }

    public void removeLable(String lable) {
        lables.remove(lable);
    }

    public List<String> getLables() {
        return lables;
    }
}
