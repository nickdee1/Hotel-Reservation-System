/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.security.model;

/**
 *
 * @author FN
 */
public class LoginStatus {
    
    private boolean loggedIn;
    private String email;
    private String errorMessage;
    private boolean success;

    public LoginStatus() {
    }

    public LoginStatus(boolean loggedIn, boolean success, String email, String errorMessage) {
        this.loggedIn = loggedIn;
        this.email = email;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
}
