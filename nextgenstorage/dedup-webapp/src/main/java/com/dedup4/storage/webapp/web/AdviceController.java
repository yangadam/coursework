package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.Feedback;
import com.dedup4.storage.webapp.repository.FeedbackRepository;
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
    private FeedbackRepository feedbackRepository;

    /**
     * @return all feedback as list if success, otherwise null
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Feedback> listAll() {
        List<Feedback> feedbackList = null;
        try {
            feedbackList = feedbackRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error when getting all feedback from database", e);
        }
        return feedbackList;
    }

    /**
     * @return feedback sent by user as list if success, otherwise null
     */
    @RequestMapping(value = "personal", method = RequestMethod.GET)
    public List<Feedback> list(Principal principal) {
        List<Feedback> feedbackList = null;
        try {
            feedbackList = feedbackRepository.findByUsername(principal.getName());
        } catch (Exception e) {
            LOGGER.error("Error when getting all feedback from database", e);
        }
        return feedbackList;
    }

    /**
     * @param feedback feedback
     * @return title of feedback if success, otherwise null
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@RequestBody Feedback feedback, Principal principal) {
        if (principal.getName() != null) {
            feedback.setUsername(principal.getName());
            try {
                feedbackRepository.insert(feedback);
                return feedback.getTitle();
            } catch (Exception e) {
                LOGGER.error("Error when adding feedback into database", e);
            }
        }
        return null;
    }

}
