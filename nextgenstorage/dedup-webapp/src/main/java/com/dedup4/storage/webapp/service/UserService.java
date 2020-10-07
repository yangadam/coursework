package com.dedup4.storage.webapp.service;

import com.dedup4.storage.webapp.domain.User;
import com.dedup4.storage.webapp.domain.UserOperation;
import com.dedup4.storage.webapp.repository.UserOperationRepository;
import com.dedup4.storage.webapp.repository.UserRepository;
import com.dedup4.storage.webapp.util.MailSender;
import com.dedup4.storage.webapp.util.PwdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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
    private UserOperationRepository userOperationRepository;

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
        UserOperation op = userOperationRepository.findByTypeAndDate(UserOperation.Type.LOGIN, new Date());
        if (op == null) {
            op = new UserOperation(UserOperation.Type.LOGIN);
        } else {
            op.setCount(op.getCount() + 1);
        }
        userOperationRepository.save(op);
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
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public String resetPassword(String username, String mail) {
        User user = userRepository.findByUsername(username);
        String actualMail = user.getProfile().getMail();
        if (StringUtils.isNoneBlank(actualMail) && actualMail.equals(mail)) {
            String randPwd = PwdGenerator.generate();
            String encryptPwd = passwordEncoder.encode(randPwd);
            try {
                new MailSender().sendMail(mail, "New Password", randPwd);
            } catch (MessagingException e) {
                return null;
            }
            user.setPassword(encryptPwd);
            userRepository.save(user);
            return username;
        }
        return null;
    }

    public String remove(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return username;
        }
        return null;
    }
}
