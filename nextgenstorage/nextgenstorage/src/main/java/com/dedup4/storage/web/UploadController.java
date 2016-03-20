package com.dedup4.storage.web;

import com.dedup4.storage.domain.User;
import com.dedup4.storage.domain.UserFile;
import com.dedup4.storage.repository.UserFileRepository;
import com.dedup4.storage.util.HdfsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping("/user/upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UserFileRepository userFileRepository;

    @RequestMapping(method = RequestMethod.GET)
    public void fileUploadForm() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(@RequestParam("dir") String dir,
                                              @RequestParam("name") String fileName,
                                              @RequestParam MultipartFile file) {
        LOGGER.info("uploading.... ");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserFile userFile = new UserFile();
        userFile.setFileName(fileName);
        userFile.setSize(file.getSize());
        userFile.setUploadDate(new Date());
        userFile.setUserId(user.getId());

        userFileRepository.insert(userFile);
        LOGGER.info("File Inserted.");

        HdfsUtil hdfsUtil = new HdfsUtil("/users/");
        if (dir == null)
            dir = "";
        String filePath = user.getUsername() + dir + "/" + fileName;
        filePath = generateFilePath(hdfsUtil, filePath);
        try {
            hdfsUtil.uploadFile(multipartToFile(file), filePath);
            // TODO: 3/12/2016
            //messageSender.send("A file uploaded: " + fileName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    private String generateFilePath(HdfsUtil hdfsUtil, String filePath) {
        String newPath = filePath;
        int count = 1;
        try {
            while (hdfsUtil.exists(filePath)) {
                newPath = filePath.substring(0, filePath.lastIndexOf('.')) + "(" + count + ")"
                        + filePath.substring(filePath.lastIndexOf('.'));
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPath;
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }


}
