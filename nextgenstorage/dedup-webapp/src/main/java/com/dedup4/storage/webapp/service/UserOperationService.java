package com.dedup4.storage.webapp.service;

import com.dedup4.storage.webapp.domain.UserOperation;
import com.dedup4.storage.webapp.repository.UserOperationRepository;
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

    public List<UserOperation> getStat(UserOperation.Type type, Date from, Date to) {
        return userOperationRepository.findByTypeAndDateBetween(type, from, to);
    }

    public void updateStat(UserOperation.Type type, long size) {
        UserOperation op = userOperationRepository.findByTypeAndDate(type, new Date());
        if (op == null) {
            op = new UserOperation(type, size);
        } else {
            op.setCount(op.getCount() + 1);
            op.setSize(op.getSize() + size);
        }
        userOperationRepository.save(op);
    }

}
