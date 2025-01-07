package com.gaboot.gabshop.product.services;

import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService extends FileService {

    /**
     * Uploads and resizes an image from ByteString.
     *
     * @param fileBytes ByteString representing the image file content.
     * @param filename  Name of the file to save (without extension).
     * @param DIR       Directory where the file will be saved.
     * @return The path of the saved image.
     */
    public String uploadImageThumb(ByteString fileBytes, String filename, String DIR) {
        // Create the upload directory if it doesn't exist
        File uploadDir = new File(DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Define the file extension (default to "png" if not known)
        String[] fileSplit = filename.split("\\.");
        String fileExtension = fileSplit[fileSplit.length-1]; // Default extension (can adjust based on use case)

        // Define the path to save the file
        Path filePath = Paths.get(DIR + filename);
        String imagePath = "";

        try {
            if (filePath.toFile().exists()) {
                deleteFile(filePath.toString());
            }

            // Convert ByteString to InputStream
            InputStream inputImage = new ByteArrayInputStream(fileBytes.toByteArray());

            // Resize the image
            BufferedImage resizedImage = resizeImage(inputImage);

            // Save the resized image
            saveImage(resizedImage, fileExtension, filePath.toString());

            imagePath = DIR + filePath.getFileName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath;
    }

    /**
     * Deletes a file if it exists.
     *
     * @param filePath Path of the file to delete.
     */
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();
        }
    }

    /**
     * Resizes an image.
     *
     * @param inputImage InputStream of the original image.
     * @return A BufferedImage of the resized image.
     * @throws IOException If an error occurs during resizing.
     */
    public BufferedImage resizeImage(InputStream inputImage) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputImage);
        final int width = Math.round((float) originalImage.getWidth() * 0.2f); // Scale down to 20%
        final int height = Math.round((float) originalImage.getHeight() * 0.2f);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, width, height, null);
        graphics.dispose();

        return resizedImage;
    }

    /**
     * Saves an image to the file system.
     *
     * @param image          The BufferedImage to save.
     * @param formatName     The image format (e.g., "png", "jpg").
     * @param outputFilePath The path where the image will be saved.
     * @throws IOException If an error occurs during saving.
     */
    public void saveImage(BufferedImage image, String formatName, String outputFilePath) throws IOException {
        File outputFile = new File(outputFilePath);
        ImageIO.write(image, formatName, outputFile);
    }
}
