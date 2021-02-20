package com.example.winterblog.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    MODER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
