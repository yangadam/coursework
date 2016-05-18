package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Mengmeng Created on May 18, 2016.
 */
@RestController
@RequestMapping("reset")
public class ResetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String resetPassword(@RequestParam String username, @RequestParam String mail) {
        try {
            return userService.resetPassword(username, mail);
        } catch (Exception e) {
            LOGGER.error("Error when reset password", e);
            return null;
        }
    }

}
