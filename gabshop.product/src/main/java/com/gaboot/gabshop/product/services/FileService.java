package com.gaboot.gabshop.product.services;

import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    /**
     * Saves a file to the specified directory.
     *
     * @param fileBytes Byte array representing the file content.
     * @param fileName  Name of the file to be created.
     * @param directory Directory where the file will be saved.
     * @return Path of the saved file.
     * @throws IOException If an error occurs while writing the file.
     */
    public String saveFile(byte[] fileBytes, String fileName, String directory) throws IOException {
        // Create the directory if it doesn't exist
        Path dirPath = Paths.get(directory);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        System.out.println("Dir Path:"+dirPath.toString());
        System.out.println("File name:"+fileName);
        // Create the file path
        Path filePath = dirPath.resolve(fileName);
        System.out.println("File Path: "+filePath.toString());

        // Write the byte array to the file
        Files.write(filePath, fileBytes);

        return filePath.toString().replace("\\", "/");
    }

    public String saveFile(ByteString fileBytes, String fileName, String directory) throws IOException {
        // Convert ByteString to byte[]
        byte[] fileData = fileBytes.toByteArray();
        return saveFile(fileData, fileName, directory);
    }


    // =======================================================

}
