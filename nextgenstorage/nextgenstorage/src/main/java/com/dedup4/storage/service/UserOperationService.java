package com.dedup4.storage.service;

import com.dedup4.storage.domain.UserOperation;
import com.dedup4.storage.repository.UserOperationRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 27, 2016.
 */
@Service
public class UserOperationService {

    @Autowired
    private UserOperationRepository userOperationRepository;

    private static Date clearDateClock(Date date) {
        return DateUtils.setHours(DateUtils.setMinutes(DateUtils.setSeconds(
                DateUtils.setMilliseconds(date, 0), 0), 0), 0);
    }

    /**
     * Total download count
     *
     * @return total download count
     */
    public int countTotalDownload() {
        return userOperationRepository.countByType(UserOperation.Type.DOWNLOAD);
    }

    /**
     * Yesterday download count
     *
     * @return yesterday download count
     */
    public int countYesterdayDownload() {
        Date to = new Date();
        to = clearDateClock(to);
        Date from = DateUtils.addDays(to, -1);
        return userOperationRepository.countByTypeAndTimeBetween(UserOperation.Type.DOWNLOAD, from, to);
    }

    /**
     * Download time information between two dates
     *
     * @param from start date
     * @param to   end date
     * @return date as list
     */
    public List<Date> getDownloadStatistics(Date from, Date to) {
        return userOperationRepository.findTimeByTypeAndTimeBetween(UserOperation.Type.DOWNLOAD, from, to);
    }

}
