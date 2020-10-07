package com.dedup4.storage.webapp.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yang Mengmeng Created on May 18, 2016.
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public LoginSuccessHandler() {
        this.setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (authentication.getName().equals("admin")) {
            this.setDefaultTargetUrl("/admin");
        } else {
            this.setDefaultTargetUrl("/index");
        }
        this.handle(httpServletRequest, httpServletResponse, authentication);
        this.clearAuthenticationAttributes(httpServletRequest);
    }

}
