package com.dedup4.storage.webapp.web;

import com.dedup4.storage.common.domain.FileRecipe;
import com.dedup4.storage.webapp.service.FileOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    private FileOperationService fileOperationService;

    @RequestMapping(method = RequestMethod.GET)
    public void handleDownload(@RequestParam String path,
                               @RequestParam String name,
                               Principal principal,
                               HttpServletResponse response) {
        FileRecipe file = fileOperationService.getFile(principal.getName(), path, name);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + name + "\"");
        try {
            try (InputStream inputStream = getInputStream(file);
                 OutputStream outputStream = response.getOutputStream()) {
                if (inputStream != null) {
                    FileCopyUtils.copy(inputStream, outputStream);
                    response.flushBuffer();
                }
            }
        } catch (IOException e) {
            LOGGER.error("Fail to download file.", e);
        }
    }

    private InputStream getInputStream(FileRecipe file) {
        if (file.isOnHdfs() // TODO
                ) {
            return null;
        } else try {
            File root = (File.listRoots())[0];
            String absPath = root.getAbsolutePath() + "tmp/dedup/upload";
            File tempFile = new File(absPath + '/' + file.getTempFileName());
            if (tempFile.exists()) {
                return new FileInputStream(tempFile);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.", e);
            return null;
        }
        return null;
    }

}
