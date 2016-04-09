package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.User;
import com.dedup4.storage.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 21, 2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.findAll();
    }

    /**
     * @return users as list if success, otherwise null
     */
    @RequestMapping(value = "registration")
    public List<User> registration() {
        List<User> userList = null;
        try {
            userList = userService.findAll();
        } catch (Exception e) {
            LOGGER.error("Error when getting users from database", e);
        }
        return userList;
    }

    /**
     * @param username username
     * @return user if found, otherwise null
     */
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public User getUser(@PathVariable String username) {
        User user = null;
        try {
            user = userService.getByUsername(username);
        } catch (Exception e) {
            LOGGER.error("Error when get user from database", e);
        }
        return user;
    }

    /**
     * @param user user to be add
     * @return user name if success, otherwise null
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            LOGGER.error("Error when adding user into database", e);
            return null;
        }
        return user.getUsername();
    }

    /**
     * @param user user to update
     * @return username if success, otherwise null
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user) {
        try {
            return userService.updateUser(user).getUsername();
        } catch (Exception e) {
            LOGGER.error("Error when update user info", e);
            return null;
        }
    }

    /**
     * @param user user to change password
     * @return user changed password
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestBody User user) {
        try {
            return userService.changePassword(user).getUsername();
        } catch (Exception e) {
            LOGGER.error("Error when change password", e);
            return null;
        }
    }

    /**
     * @param username username
     * @return true if username exists
     */
    @RequestMapping("exist")
    public Boolean isExist(@RequestParam String username) {
        return userService.doesUserExist(username);
    }

}
