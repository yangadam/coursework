package com.dedup4.storage.web;

import com.dedup4.storage.domain.User;
import com.dedup4.storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yang Mengmeng Created on Mar 20, 2016.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String register(User user) {
        userRepository.save(user);
        return "/login";
    }

    @RequestMapping("isExist")
    @ResponseBody
    public Boolean isExist(@RequestParam String username) {
        return userRepository.findByUsername(username) == null;
    }

}
