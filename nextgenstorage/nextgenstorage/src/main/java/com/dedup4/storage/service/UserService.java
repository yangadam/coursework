package com.dedup4.storage.service;

import com.dedup4.storage.domain.User;
import com.dedup4.storage.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 14, 2016.
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Load user by username and update last modified date
     *
     * @param username username
     * @return user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }
        user.setLastModifiedDate(new Date());
        userRepository.save(user);
        return user;
    }

    /**
     * Encrypt password and then insert user to database
     *
     * @param user user to add
     */
    public void addUser(User user) {
        String encryptPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPwd);
        userRepository.insert(user);
    }

    /**
     * Judge if username exists
     *
     * @param username username
     * @return true if user with this username exists
     */
    public boolean doesUserExist(String username) {
        return userRepository.findByUsername(username) == null;
    }

    /**
     * Get all users
     *
     * @return users as list
     */
    public List<User> findAll() {
        return userRepository.findAll(new Sort(Sort.Direction.DESC, "createDate"));
    }

    /**
     * Get a user by username
     *
     * @param username username
     * @return user
     */
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Update the information of user
     *
     * @param user user to update
     * @return user updated
     */
    public User updateUser(User user) {
        User userFound = userRepository.findByUsername(user.getUsername());
        user.setId(userFound.getId());
        return userRepository.save(user);
    }

    /**
     * Change the password of user
     *
     * @param user user to change password
     * @return user if old password matches, otherwise null
     */
    public User changePassword(User user) {
        User userFound = userRepository.findByUsername(user.getUsername());
        if (passwordEncoder.matches(user.getPassword(), userFound.getPassword())) {
            String encryptPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptPwd);
            userRepository.insert(user);
            return user;
        }
        return null;
    }
}
