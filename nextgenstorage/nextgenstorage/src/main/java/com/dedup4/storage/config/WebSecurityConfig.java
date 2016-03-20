package com.dedup4.storage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index", true).permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        auth.userDetailsService(userDetailsService);//.passwordEncoder(encoder);
    }

}
