package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.Advice;
import com.dedup4.storage.webapp.repository.AdviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
@RestController
@RequestMapping("/advice")
public class AdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @Autowired
    private AdviceRepository adviceRepository;

    /**
     * @return all advice as list if success, otherwise null
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Advice> list() {
        List<Advice> adviceList = null;
        try {
            adviceList = adviceRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error when getting all advice from database", e);
        }
        return adviceList;
    }

    /**
     * @param advice advice
     * @return title of advice if success, otherwise null
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@RequestBody Advice advice, Principal principal) {
        if (principal.getName() != null) {
            advice.setUsername(principal.getName());
            try {
                adviceRepository.insert(advice);
                return advice.getTitle();
            } catch (Exception e) {
                LOGGER.error("Error when adding advice into database", e);
            }
        }
        return null;
    }

}
