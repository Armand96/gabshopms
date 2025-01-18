package com.gaboot.services.images;

import com.gaboot.services.files.FileInterface;
// import com.gaboot.services.files.FileService;
// import com.gaboot.services.files.MinIoFileService;
import com.google.protobuf.ByteString;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageService  {

    private final FileInterface fileService;

    // Inject FileInterface (either FileService or MinioFileService)
    public ImageService(FileInterface fileService) {
        this.fileService = fileService;
    }

    /**
     * Uploads and resizes an image from ByteString.
     *
     * @param fileBytes ByteString representing the image file content.
     * @param filename  Name of the file to save (without extension).
     * @param DIR       Directory where the file will be saved.
     * @return The path of the saved image.
     */
    public String uploadImageThumb(ByteString fileBytes, String filename, String DIR) {

        // Define the file extension (default to "png" if not known)
        String[] fileSplit = filename.split("\\.");
        String fileExtension = fileSplit[fileSplit.length-1]; // Default extension (can adjust based on use case)

        // Define the path to save the file
        Path filePath = Paths.get(DIR + filename);
        String imagePath = "";

        try {
            // Convert ByteString to InputStream
            InputStream inputImage = new ByteArrayInputStream(fileBytes.toByteArray());

            // Resize the image
            BufferedImage resizedImage = resizeImage(inputImage);

            // Save the resized image
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, fileExtension, baos);
            ByteString byteString = ByteString.copyFrom(baos.toByteArray());
            saveImage(byteString, fileExtension, filePath.toString());

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
     * @param image          The ByteString to save.
     * @param fileName     The image format (e.g., "png", "jpg").
     * @param directory The path where the image will be saved.
     * @throws IOException If an error occurs during saving.
     */
    public String saveImage(ByteString image, String fileName, String directory) throws IOException {
        return fileService.saveFile(image.toByteArray(), fileName, directory);
    }
}
