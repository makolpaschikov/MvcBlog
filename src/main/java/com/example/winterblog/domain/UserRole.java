package com.example.winterblog.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author makolpaschikov
 */
public enum UserRole implements GrantedAuthority {
    ADMIN,
    MODER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
