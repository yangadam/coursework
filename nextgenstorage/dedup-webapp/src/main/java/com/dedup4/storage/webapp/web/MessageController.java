package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.Message;
import com.dedup4.storage.webapp.repository.MessageRepository;
import com.google.common.collect.Lists;
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
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @RequestMapping(value = "receive", method = RequestMethod.GET)
    public List<Message> receive(Principal principal) {
        return messageRepository.findByUsernameToIn(Lists.newArrayList("", principal.getName()));
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String post(@RequestBody Message message, Principal principal) {
        try {
            message.setLastModifiedUser(principal.getName());
            messageRepository.insert(message);
            return message.getHeader();
        } catch (Exception e) {
            LOGGER.error("Error when insert message into db.", e);
        }
        return null;
    }

}
