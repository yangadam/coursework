package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.UserOperation;
import com.dedup4.storage.webapp.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 28, 2016.
 */
@RestController
@RequestMapping("/stat")
public class UserOperationController {

    @Autowired
    private UserOperationService userOperationService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public int loginStat(@RequestParam Date from, @RequestParam Date to) {
        List<UserOperation> operations = userOperationService.getStat(UserOperation.Type.LOGIN, from, to);
        int totalCount = 0;
        for (UserOperation operation : operations) {
            totalCount += operation.getCount();
        }
        return totalCount;
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public long[] uploadStat(@RequestParam Date from, @RequestParam Date to) {
        List<UserOperation> operations = userOperationService.getStat(UserOperation.Type.UPLOAD, from, to);
        int totalCount = 0;
        long totalSize = 0;
        for (UserOperation operation : operations) {
            totalCount += operation.getCount();
            totalSize += operation.getSize();
        }
        return new long[]{totalCount, totalSize};
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public long[] downloadStat(@RequestParam Date from, @RequestParam Date to) {
        List<UserOperation> operations = userOperationService.getStat(UserOperation.Type.DOWNLOAD, from, to);
        int totalCount = 0;
        long totalSize = 0;
        for (UserOperation operation : operations) {
            totalCount += operation.getCount();
            totalSize += operation.getSize();
        }
        return new long[]{totalCount, totalSize};
    }

}
