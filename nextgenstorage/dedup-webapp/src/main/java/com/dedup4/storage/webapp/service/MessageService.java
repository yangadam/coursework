package com.dedup4.storage.webapp.service;

import com.dedup4.storage.webapp.domain.Feedback;
import com.dedup4.storage.webapp.domain.Notification;
import com.dedup4.storage.webapp.repository.FeedbackRepository;
import com.dedup4.storage.webapp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on May 18, 2016.
 */
@Service
public class MessageService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    public void postNotification(Notification notification) {
        notificationRepository.insert(notification);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbackByUsername(String username) {
        return feedbackRepository.findByUsername(username);
    }

    public Boolean commitFeedback(Feedback feedback) {
        feedback.setDate(new Date());
        feedbackRepository.insert(feedback);
        return true;
    }

    public void replyFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
