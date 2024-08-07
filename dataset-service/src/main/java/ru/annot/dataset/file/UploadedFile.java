package ru.annot.dataset.file;

public class UploadedFile {

    private Long datasetId;
    private String key;
    private String filename;

    public UploadedFile(Long datasetId, String key, String filename) {
        this.datasetId = datasetId;
        this.key = key;
        this.filename = filename;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
