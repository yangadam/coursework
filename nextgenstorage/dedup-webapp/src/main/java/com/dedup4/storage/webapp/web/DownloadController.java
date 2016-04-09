package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.FileRecipe;
import com.dedup4.storage.webapp.service.FileOperationService;
import com.dedup4.storage.webapp.util.HdfsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            InputStream inputStream = getInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            FileCopyUtils.copy(inputStream, outputStream);
            response.flushBuffer();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error("Fail to download file.", e);
        }
    }

    private InputStream getInputStream(FileRecipe file) {
        if (file.isOnHdfs()
                ) {

        } else try {
            return new FileInputStream(new File("upload/" + file.getTempFileName()));
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.", e);
            return null;
        }

        return null;
    }

    public InputStream getDownload(String fileName, String dir) throws IOException {
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        fileName = new String(fileName.getBytes(), "ISO8859-1");
        if (dir == null) {
            dir = "";
        }
        String fullFilePath = user.getUsername() + dir + "/" + fileName;
        // TODO check its location
        return hdfsUtil.readFile(fullFilePath);
    }

}
