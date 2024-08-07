package ru.annot.layout.dataset;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

@ApplicationScoped
public class MongoDatasetRepository implements DatasetRepository {

    private static final String DATASET_DATABASE = "dataset";
    private static final String DATASET_COLLECTION = "dataset";
    private static final String DATASET_ID_KEY = "id";
    private static final String DATASET_NAME_KEY = "name";
    private static final String DATASET_DESCRIPTION_KEY = "description";

    @Inject
    MongoClient mongoClient;

    @Override
    public void createDataset(Long id, String name, String description) {
        Document document = new Document();
        document.append(DATASET_ID_KEY, id);
        document.append(DATASET_NAME_KEY, name);
        document.append(DATASET_DESCRIPTION_KEY, description);
        getCollection().insertOne(document);
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase(DATASET_DATABASE).getCollection(DATASET_COLLECTION);
    }
}
