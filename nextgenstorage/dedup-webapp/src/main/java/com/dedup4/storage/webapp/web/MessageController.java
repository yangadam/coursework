package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.Feedback;
import com.dedup4.storage.webapp.domain.Notification;
import com.dedup4.storage.webapp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
@RestController
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public List<Notification> listNotification() {
        return messageService.getAllNotification();
    }

    @RequestMapping(value = "/notification/post", method = RequestMethod.POST)
    public String post(@RequestBody Notification notification, Principal principal) {
        try {
            notification.setCreatedDate(new Date());
            notification.setLastModifiedDate(new Date());
            notification.setLastModifiedUser(principal.getName());
            messageService.postNotification(notification);
            return notification.getTitle();
        } catch (Exception e) {
            LOGGER.error("Error when insert notification into db.", e);
        }
        return null;
    }

    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    public List<Feedback> listFeedback(Principal principal) {
        if (principal.getName().equals("admin")) {
            return messageService.getAllFeedback();
        } else {
            return messageService.getFeedbackByUsername(principal.getName());
        }
    }

    @RequestMapping(value = "feedback/commit", method = RequestMethod.POST)
    public Boolean commitFeedback(@RequestBody Feedback feedback) {
        return messageService.commitFeedback(feedback);
    }

    @RequestMapping(value = "feedback/reply")
    public void replyFeedback(@RequestBody Feedback feedback) {
        messageService.replyFeedback(feedback);
    }

}
