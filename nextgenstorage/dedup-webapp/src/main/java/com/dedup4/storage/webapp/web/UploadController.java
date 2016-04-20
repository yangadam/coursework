package com.dedup4.storage.webapp.web;

import com.dedup4.storage.common.domain.FileRecipe;
import com.dedup4.storage.common.domain.LogicFile;
import com.dedup4.storage.webapp.service.FileOperationService;
import com.dedup4.storage.webapp.util.MultipartFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * When user click upload button, send a GET request to check if md5 exists
 * Upload file if receive false, otherwise do nothing.
 *
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FileOperationService fileOperationService;

    @RequestMapping(method = RequestMethod.POST)
    public Boolean upload(@RequestParam String path,
                          MultipartFile file,
                          Principal principal) {
        return this.receiveFile(path, file, principal.getName());
    }

    @Deprecated
    @RequestMapping(value = "/legacy", method = RequestMethod.POST)
    public Boolean legacyUpload(@RequestParam String path, @RequestParam String md5,
                                MultipartFile file, Principal principal) {
        return false;
    }

    @Deprecated
    @RequestMapping(value = "legacy/checkMd5", method = RequestMethod.GET)
    public Boolean checkMd5(@RequestParam String path, @RequestParam String md5, Principal principal) {
        FileRecipe fileRecipe = fileOperationService.getByMd5(md5);
        if (fileRecipe != null) {
            fileOperationService.saveLogicFile(principal.getName(), path,
                    fileRecipe.getActualFileName(), md5, fileRecipe.getFileSize());
            return true;
        }
        return false;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private Boolean receiveFile(String path, MultipartFile file, String currentUser) {
        LOGGER.info("uploading.... ");

        String md5 = MultipartFileUtil.md5(file);
        FileRecipe fileRecipe = fileOperationService.getByMd5(md5);
        if (fileRecipe != null) {
            fileOperationService.saveLogicFile(currentUser, path,
                    fileRecipe.getActualFileName(), md5, fileRecipe.getFileSize());
            return true;
        }
        try {
            File root = (File.listRoots())[0];
            String absPath = root.getAbsolutePath() + "tmp/dedup/upload";
            File folder = new File(absPath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File tempFile = new File(absPath + '/' + md5);
            if (!tempFile.exists()) {
                file.transferTo(tempFile);
            }
        } catch (IOException e) {
            LOGGER.error("Error when save file.", e);
            return false;
        }
        LogicFile logicFile = fileOperationService.saveLogicFile(currentUser,
                path, file.getOriginalFilename(), md5, file.getSize());
        fileOperationService.saveFileRecipe(logicFile);
        LOGGER.info("File Inserted.");
        return true;
    }

}
