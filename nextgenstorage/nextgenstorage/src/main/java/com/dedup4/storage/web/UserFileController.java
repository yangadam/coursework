package com.dedup4.storage.web;

import com.dedup4.storage.domain.UserFile;
import com.dedup4.storage.repository.UserFileRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
@RestController
@RequestMapping("/userFile")
public class UserFileController {

    @Autowired
    private UserFileRepository userFileRepository;

    @RequestMapping(value = "hotDownload", method = RequestMethod.GET)
    public List<UserFile> hotDownload() {
        return userFileRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "downloadCount")));
    }

    @RequestMapping(value = "totalUpload", method = RequestMethod.GET)
    public long totalUpload() {
        return userFileRepository.count();
    }

    @RequestMapping(value = "yesterdayUpload", method = RequestMethod.GET)
    public int yesterdayUpload() {
        Date to = new Date();
        Date from = DateUtils.addDays(to, -1);
        return userFileRepository.countByUploadDateBetween(from, to);
    }

}
