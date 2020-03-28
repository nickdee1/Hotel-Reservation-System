/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.rsp.server.security.model.LoginStatus;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public class AuthenticationFailure implements AuthenticationFailureHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFailure.class);

    private final ObjectMapper mapper;

    @Autowired
    public AuthenticationFailure(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
        LOG.debug("Login failed for user {}.", hsr.getParameter(SecurityConstants.EMAIL_PARAM));
        final LoginStatus status = new LoginStatus(false, false, null, ae.getMessage());
        mapper.writeValue(hsr1.getOutputStream(), status);
    }
    
}
