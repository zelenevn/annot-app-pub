package ru.annot.layout.classification;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MongoClassificationDatasetRepositoryImpl implements ClassificationDatasetRepository {

    private static final String CLASSIFICATION_DATASET_BASE = "dataset";
    private static final String CLASSIFICATION_COLLECTION = "classification";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String DESCRIPTION_KEY = "description";
    private static final String LABELS_KEY = "labels";

    @Inject
    MongoClient mongoClient;

    @Override
    public void save(ClassificationDataset classificationDataset) {
        Document document = new Document()
                .append(ID_KEY, classificationDataset.getId())
                .append(NAME_KEY, classificationDataset.getName())
                .append(DESCRIPTION_KEY, classificationDataset.getDescription())
                .append(LABELS_KEY, classificationDataset.getLables());
        getCollection().insertOne(document);
    }

    @Override
    public void update(ClassificationDataset classificationDataset) {
        long id = classificationDataset.getId();
        Document updateDocument = new Document()
                .append(ID_KEY, classificationDataset.getId())
                .append(NAME_KEY, classificationDataset.getName())
                .append(DESCRIPTION_KEY, classificationDataset.getDescription())
                .append(LABELS_KEY, classificationDataset.getLables());
        getCollection().findOneAndUpdate(Filters.eq("id", id), new Document("$set", updateDocument));
    }

    @Override
    public List<String> getLablesByDatasetId(Long id) {
        ClassificationDataset dataset = findById(id);
        return dataset.getLables();
    }

    public List<ClassificationDataset> findAll() {
        List<ClassificationDataset> datasets = new ArrayList<>();
        try(MongoCursor<Document> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Long id = document.getLong(ID_KEY);
                String name = document.getString(NAME_KEY);
                String description = document.getString(DESCRIPTION_KEY);
                List<String> labels = document.getList(LABELS_KEY, String.class);
                ClassificationDataset classificationDataset = new ClassificationDataset(id, name, description, labels);
                datasets.add(classificationDataset);
            }
        }
        return datasets;
    }

    @Override
    public ClassificationDataset findById(Long id) {
        Document foundDocument = getCollection().find(Filters.eq("id", id)).first();
        Long classificationDatasetId = foundDocument.getLong(ID_KEY);
        String name = foundDocument.getString(NAME_KEY);
        String description = foundDocument.getString(DESCRIPTION_KEY);
        List<String> labels = foundDocument.getList(LABELS_KEY, String.class);
        return new ClassificationDataset(classificationDatasetId, name, description, labels);
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase(CLASSIFICATION_DATASET_BASE).getCollection(CLASSIFICATION_COLLECTION);
    }
}
