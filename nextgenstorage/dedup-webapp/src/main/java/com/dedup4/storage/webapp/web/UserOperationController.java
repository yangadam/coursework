package com.dedup4.storage.webapp.web;

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
@RequestMapping("/operation")
public class UserOperationController {

    @Autowired
    private UserOperationService userOperationService;

    /**
     * Total download count
     *
     * @return total download count
     */
    @RequestMapping(value = "totalCount", method = RequestMethod.GET)
    public int totalDownloadCount() {
        return userOperationService.countTotalDownload();
    }

    /**
     * Yesterday download count
     *
     * @return yesterday download count
     */
    @RequestMapping(value = "yesterdayCount", method = RequestMethod.GET)
    public int yesterdayDownloadCount() {
        return userOperationService.countYesterdayDownload();
    }

    /**
     * Download statistics between two dates
     *
     * @param from start date
     * @param to   end date
     * @return download time as list
     */
    @RequestMapping(value = "statistics", method = RequestMethod.GET)
    public List<Date> dd(@RequestParam Date from, @RequestParam Date to) {
        return userOperationService.getDownloadStatistics(from, to);
    }

}
