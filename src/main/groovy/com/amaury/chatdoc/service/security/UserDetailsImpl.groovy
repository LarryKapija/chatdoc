package com.amaury.chatdoc.service.security

import com.amaury.chatdoc.data.entity.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl implements UserDetails {
    static final long serialVersionUID = 1L
    String username
    String email
    @JsonIgnore
    String password
    Collection<? extends GrantedAuthority> authorities
    UserDetailsImpl(String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.username = username
        this.email = email
        this.password = password
        this.authorities = authorities
    }
    static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().collect {new SimpleGrantedAuthority(it.getName())}
        new UserDetailsImpl(user.getName(),user.getEmail(),user.getPassword(),authorities)
    }
    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        authorities
    }

    @Override
    boolean isAccountNonExpired() {
        true;
    }
    @Override
    boolean isAccountNonLocked() {
        true
    }
    @Override
    boolean isCredentialsNonExpired() {
        true
    }
    @Override
    boolean isEnabled() {
        true
    }
    @Override
    boolean equals(Object o) {
        if (this == o)
            return true
        if (o == null || getClass() != o.getClass())
            return false
        UserDetailsImpl user = (UserDetailsImpl) o
        Objects.equals(username, user.username)
    }
}
