/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.security;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author FN
 */
@WebListener
public class SessionTimeoutManager implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(SecurityConstants.SESSION_TIMEOUT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // do nothing
    }
    
}
