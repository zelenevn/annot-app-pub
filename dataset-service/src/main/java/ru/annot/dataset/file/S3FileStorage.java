package ru.annot.dataset.file;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class S3FileStorage implements FileStorage {

    private static final Logger LOGGER = Logger.getLogger(S3FileStorage.class);

    private static final String FILENAME_METADATA_KEY = "filename";
    private static final String FILE_EXTENSION_DELIMITER = ".";

    @Inject
    S3Client s3Client;

    @ConfigProperty(name = "bucket")
    String bucket;

    @Override
    public String saveFile(String filename, File file) {
        String key = generateFileKey(filename);
        PutObjectRequest request = buildPutObjectRequest(key, filename);
        RequestBody body = RequestBody.fromFile(file);
        s3Client.putObject(request, body);
        LOGGER.infof("Сохранен файл %s с ключом %s", filename, key);
        return key;
    }

    @Override
    public InputStream getByKey(String key) {
        ResponseBytes<GetObjectResponse> response = s3Client.getObjectAsBytes(buildGetObjectRequest(key));
        return response.asInputStream();
    }

    private String generateFileKey(String filename) {
        String fileExtension = FilenameUtils.getExtension(filename);
        return UUID.randomUUID() + FILE_EXTENSION_DELIMITER + fileExtension;
    }

    private PutObjectRequest buildPutObjectRequest(String key, String filename) {
        Map<String, String> metadata = new HashMap<>() {{
            put(FILENAME_METADATA_KEY, filename);
        }};

        return PutObjectRequest.builder()
                .bucket(bucket)
                .metadata(metadata)
                .key(key)
                .build();
    }

    private GetObjectRequest buildGetObjectRequest(String key) {
        return GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
    }
}
