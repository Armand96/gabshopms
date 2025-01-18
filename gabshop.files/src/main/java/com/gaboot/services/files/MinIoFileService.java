package com.gaboot.services.files;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.errors.MinioException;

import com.google.protobuf.ByteString;
import io.minio.messages.Item;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
// import java.nio.file.Path;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinIoFileService implements FileInterface {
    private final MinioClient minioClient;
    private final String bucketName;
    private final String urlEndpoint;

    public MinIoFileService(String minioUrl, String accessKey, String secretKey, String bucketName) {
        urlEndpoint = minioUrl;
        this.minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
        this.bucketName = bucketName;
    }

    /**
     * Saves a file to the specified MinIO bucket.
     *
     * @param fileBytes Byte array representing the file content.
     * @param fileName  Name of the file to be saved in the bucket.
     * @return URL of the uploaded file in MinIO.
     * @throws IOException If an error occurs during upload.
     */
    @Override
    public String saveFile(byte[] fileBytes, String fileName, String directory) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(fileBytes)) {
            // Upload the file to MinIO
            directory = !directory.endsWith("/") ? directory : directory.substring(0, directory.length() - 1);
            // System.out.println("directory: "+directory);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(directory + "/" + fileName)
                    .stream(inputStream, fileBytes.length, -1)
                    .contentType(Files.probeContentType(Paths.get(fileName)))
                    .build());

            // Return the file URL
            return String.format("%s/%s/%s/%s", urlEndpoint, bucketName, directory, fileName);
        } catch (MinioException e) {
            throw new IOException("Failed to upload file to MinIO", e);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String saveFile(ByteString fileBytes, String fileName, String directory) {
        try {
            return saveFile(fileBytes.toByteArray(), fileName, directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFile(String fileNameDirectory) {
        return false;
    }

    public boolean directoryExists(String prefix) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix(prefix)
                        .recursive(true) // Recursive to check everything under the prefix
                        .build()
        );

        for (Result<Item> result : results) {
            if (result.get().objectName().startsWith(prefix)) {
                return true; // Directory (prefix) exists
            }
        }
        return false;
    }

    public void createDirectory(String prefix) throws Exception {
        if (!prefix.endsWith("/")) {
            prefix += "/";
        }
        // Upload a dummy file to simulate the directory
        ByteArrayInputStream emptyStream = new ByteArrayInputStream(new byte[0]);
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(prefix) // The prefix ends with '/'
                        .stream(emptyStream, 0, -1)
                        .build()
        );
    }
}
