package com.huangbusiness.security.user;

import com.huangbusiness.repository.entity.RoleEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class MyUserDetails implements UserDetails {

    private final Integer userId;
    @Getter
    private final String email;
    private final String name;
    private final String password;

    private final List<SimpleGrantedAuthority> authorities;

    public MyUserDetails(Integer userId, String email, String name, String password, List<RoleEntity> roles) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.authorities = roles.stream()
                .map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString())
                )
                .toList();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
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
}
