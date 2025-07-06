package com.german.proyecto.backend.helpers;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public final class FilesHelper {
    private FilesHelper() {}

    static public String[] upload(MultipartFile[] files, String storagePath) throws Exception {
        ArrayList<String> fileNames = new ArrayList<String>();
        String name;

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                name = upload(file, storagePath);
                fileNames.add(name);
            }
        }

        String[] names = fileNames.toArray(new String[0]);
        return names;
    }

    static public String upload(MultipartFile file, String storagePath) throws Exception {
        try {
            Path saveFolder = createSaveFolder(storagePath);
            String updatedFileName = updateFileName(file.getOriginalFilename());
            Path savePath = getSavePath(saveFolder, updatedFileName);
            saveFile(file, savePath);

            return updatedFileName;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al subir el archivo", e);
        }
    }

    static private Path createSaveFolder(String folder) throws Exception {
        Path path = Paths.get(folder);
        Files.createDirectories(path);
        return path;
    }

    static private String updateFileName(String fileName) {
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        String updatedName = UUID.randomUUID().toString() + "." + fileExtension;
        return updatedName;
    }

    static private Path getSavePath(Path filePath, String fileName) {
        return filePath.resolve(fileName);
    }

    static private void saveFile(MultipartFile originFile, Path destinationFile) throws Exception {
        originFile.transferTo(destinationFile.toFile());
    }
}
