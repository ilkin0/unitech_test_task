package com.ilkinmehdiyev.unitech_test_task.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class User implements UserDetails {

    private String username;
    private String password;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<>

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
