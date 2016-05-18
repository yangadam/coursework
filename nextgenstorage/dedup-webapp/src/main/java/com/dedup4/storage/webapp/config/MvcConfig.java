package com.dedup4.storage.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index");
        registry.addViewController("/login");
        registry.addViewController("/register");
        registry.addViewController("/messages");
        registry.addViewController("/addUser");
        registry.addViewController("/admin");
        registry.addViewController("/adviceList");
        registry.addViewController("/advise");
        registry.addViewController("/basicInfo");
        registry.addViewController("/changePwd");
        registry.addViewController("/complaintList");
        registry.addViewController("/downloadStatistics");
        registry.addViewController("/loginStatistics");
        registry.addViewController("/modifyBasicInfo");
        registry.addViewController("/noticeList");
        registry.addViewController("/resetPwd");
        registry.addViewController("/sharing");
        registry.addViewController("/uploadStatistics");
        registry.addViewController("/userList");
        registry.addViewController("/sendNotice");
        registry.addViewController("/basicInfoAdmin");
        registry.addViewController("/changePwdAdmin");
        registry.addViewController("/modifyBasicInfoAdmin");
    }

}
