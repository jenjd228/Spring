package org.example.app.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class DefaultService {

    private final Logger logger = Logger.getLogger(DefaultService.class);

    public void fileUpload(MultipartFile file) {
        File path = new File("upload");
        logger.info("11   " + file.getOriginalFilename());
        logger.info("22   " + path.exists());
        if (!path.exists()) {
            path.mkdir();
        }
        logger.info("33   " + path.exists());
        try {
            File file1 = new File("upload\\"+file.getOriginalFilename());
            file1.createNewFile();
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
