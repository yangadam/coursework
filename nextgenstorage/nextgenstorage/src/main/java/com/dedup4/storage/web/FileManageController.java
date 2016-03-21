package com.dedup4.storage.web;

import com.dedup4.storage.domain.User;
import com.dedup4.storage.util.HdfsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping(value = "/file")
public class FileManageController {

    @RequestMapping(value = "deleteFile")
    public ResponseEntity<?> deleteFile(@RequestParam("fileName") String fileName,
                                        @RequestParam("dir") String dir) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");
        try {
            hdfsUtil.deleteFile(user.getUsername() + dir, fileName);
        } catch (Exception e) {
            // TODO
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        // TODO Update mongodb

        // TODO update HDFS

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "mkdir")
    public ResponseEntity<?> mkdir(@RequestParam("dirPath") String dirPath,
                        @RequestParam("dir") String dir) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");
        try {
            hdfsUtil.mkdir(user.getUsername() + dir, dirPath);
        } catch (IOException e) {
            // TODO
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "deleteDir")
    public ResponseEntity<?> delDir(@RequestParam("dir") String dir) throws Exception {
        boolean ok;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");

        String path = user.getUsername() + dir;

        // TODO update all records in the dir

        dir = dir.substring(0, dir.lastIndexOf('/'));
        ok = hdfsUtil.deleteDir(path);
        if (ok)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "exist")
    public Boolean exist(@RequestParam("fileName") String fileName,
                        @RequestParam("dir") String dir) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");
        try {
            return hdfsUtil.exists(user.getUsername() + dir + "/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @RequestMapping(value = "hasSubDir")
    public Boolean hasSubDir(@RequestParam("dir") String dir) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");
        String path = user.getUsername() + dir;
        return hdfsUtil.hasSubDir(path);
    }

}
