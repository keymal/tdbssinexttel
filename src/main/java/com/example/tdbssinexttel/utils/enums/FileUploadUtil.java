package com.example.tdbssinexttel.utils.enums;

import org.springframework.web.multipart.MultipartFile;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {

            Files.createDirectory(uploadPath);


        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException ex) {

            throw new IOException("Impossible d'enregistrer le fichier");

        }
    }

    public static  void cleanDir(String dir){
        Path dirPath = Paths.get(dir);

        try{
            Files.list(dirPath).forEach(file -> {
                try {

                    Files.delete(file);
                }catch (IOException ex){

                }
            });

        }catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}
