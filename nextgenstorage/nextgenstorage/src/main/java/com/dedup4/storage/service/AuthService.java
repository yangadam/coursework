package com.dedup4.storage.service;

import com.dedup4.storage.domain.User;
import com.dedup4.storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Yang Mengmeng Created on Mar 14, 2016.
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return user;
    }

}
