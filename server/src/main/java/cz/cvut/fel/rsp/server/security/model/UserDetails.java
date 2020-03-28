/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.security.model;
import cz.cvut.fel.rsp.server.Model.Enums.UserRoleEnum;
import cz.cvut.fel.rsp.server.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 *
 * @author FN
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    
    private User user;
    
    private final List<GrantedAuthority> authorities;

    public UserDetails(User user, Set<GrantedAuthority> authorities) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(authorities);
        this.user = user;
        this.authorities = new ArrayList<>();
        this.authorities.addAll(authorities);
        addUserRole();
    }

    public UserDetails(User user) {
        Objects.requireNonNull(user);
        this.user = user;
        this.authorities = new ArrayList<>();
        addUserRole();
    }  
    
    private void addUserRole() {
        List<UserRoleEnum> roles = user.getRoles();
        for(UserRoleEnum role: roles) {
            authorities.add(new SimpleGrantedAuthority((role.toString())));
        }
    }    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
     public User getUser() {
        return user;
    }
    
}
