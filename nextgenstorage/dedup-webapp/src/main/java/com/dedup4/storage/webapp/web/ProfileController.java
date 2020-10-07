package com.dedup4.storage.webapp.web;

import com.dedup4.storage.webapp.domain.Profile;
import com.dedup4.storage.webapp.domain.User;
import com.dedup4.storage.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Yang Mengmeng Created on May 17, 2016.
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户查看个人信息
     *
     * @param principal
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Profile viewProfile(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return user.getProfile();
    }

    /**
     * 管理员查看用户个人信息
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public Profile viewProfile(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return user.getProfile();
    }

    /**
     * 用户修改个人信息
     *
     * @param profile
     * @param principal
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Profile changeProfile(@RequestBody Profile profile, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        user.setProfile(profile);
        userService.updateUser(user);
        return user.getProfile();
    }

    /**
     * 管理员修改用户个人信息
     *
     * @param username
     * @param profile
     * @return
     */
    @RequestMapping(value = "{username}", method = RequestMethod.POST)
    public Profile changeProfile(@PathVariable String username, @RequestBody Profile profile) {
        User user = userService.getByUsername(username);
        user.setProfile(profile);
        userService.updateUser(user);
        return user.getProfile();
    }

}
