package com.dedup4.storage.web;

import com.dedup4.storage.domain.User;
import com.dedup4.storage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author Yang Mengmeng Created on Mar 20, 2016.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        return "/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@ModelAttribute User user) {
        user.setRegisterDate(new Date());
        try {
            userService.addUser(user);
        } catch (Exception e) {
            LOGGER.info("Error to add user: " + e);
            return "register";
        }
        return "login";
    }

}
