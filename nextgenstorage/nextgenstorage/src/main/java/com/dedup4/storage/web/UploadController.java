package com.dedup4.storage.web;

import com.dedup4.storage.domain.File;
import com.dedup4.storage.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Boolean upload(@RequestParam String path,
                          MultipartFile file,
                          Principal principal) {
        LOGGER.info("uploading.... ");
        String currentUser = principal.getName();
        String tempFileName = file.getOriginalFilename() + currentUser + System.currentTimeMillis();
        try {
            file.transferTo(new java.io.File(tempFileName));
        } catch (IOException e) {
            LOGGER.info("Error when save file.");
            return false;
        }
        File rootFolder = fileRepository.findByOwner(currentUser);
        if (rootFolder == null) {
            rootFolder = File.createRootFolder(currentUser);
        }
        File logicFile = rootFolder.addFile(path, file.getOriginalFilename(), file.getSize());
        logicFile.setTempFileName(tempFileName);
        fileRepository.save(rootFolder);
        LOGGER.info("File Inserted.");
        return true;
    }

}
