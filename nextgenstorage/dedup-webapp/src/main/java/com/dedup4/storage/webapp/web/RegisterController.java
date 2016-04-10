package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.User;
import com.dedup4.storage.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Mengmeng Created on Apr 10, 2016.
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    /**
     * @param user user to be add
     * @return user name if success, otherwise null
     */
    @RequestMapping(method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            LOGGER.error("Error when adding user into database", e);
            return null;
        }
        return user.getUsername();
    }

}
