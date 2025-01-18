package com.gaboot.services.files;

public class FileServiceFactory {

    private final String minioUrl;
    private final String accessKey;
    private final String secretKey;
    private final String bucketName;

    public FileServiceFactory(String minioUrl, String accessKey, String secretKey, String bucketName) {
        this.minioUrl = minioUrl;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
    }

    public FileInterface getFileService(String type) {
        if ("minio".equalsIgnoreCase(type)) {
            return new MinIoFileService(minioUrl, accessKey, secretKey, bucketName);
        } else {
            return new FileService();
        }
    }
}

