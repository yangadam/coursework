package com.dedup4.storage.web;

import com.dedup4.storage.util.HdfsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping(value = "/user/DownloadFile")
public class DownloadController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> handleDownload(@RequestParam("fileName") String fileName,
                                            @RequestParam("dir") String dir, HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
        try {
            InputStream inputStream = getDownload(fileName, dir);
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

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
