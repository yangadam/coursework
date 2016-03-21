package com.dedup4.storage.web;

import com.dedup4.storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Mengmeng Created on Mar 21, 2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("exist")
    public Boolean isExist(@RequestParam String username) {
        return userService.doesUserExist(username);
    }

}
