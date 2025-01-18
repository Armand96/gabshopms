package com.gaboot.services.files;

import com.google.protobuf.ByteString;

import java.io.IOException;

public interface FileInterface {
    String saveFile(byte[] fileBytes, String fileName, String directory) throws IOException;
    String saveFile(ByteString fileBytes, String fileName, String directory) throws IOException;
    boolean deleteFile(String fileNameDirectory);
}
