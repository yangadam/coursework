package com.dedup4.storage.web;

import com.dedup4.storage.util.FileInfo;
import com.dedup4.storage.util.HdfsUtil;
import com.google.common.collect.Lists;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping(value = "/user/showDirs")
public class DirController {

    @RequestMapping(method = RequestMethod.GET)
    public List<FileInfo> show(@RequestParam("dir") String dir) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HdfsUtil hdfsUtil = new HdfsUtil("/users/");

        String fullPath = user.getUsername() + dir;
        try {
            List<FileInfo> list = hdfsUtil.getDirectoryFromHdfs(fullPath);
            for (FileInfo aList : list) {
                if (aList.getIsDirectory() == 1)
                    continue;
                // TODO get its size from mongodb
            }
            Collections.sort(list);
            return list;
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }

}
