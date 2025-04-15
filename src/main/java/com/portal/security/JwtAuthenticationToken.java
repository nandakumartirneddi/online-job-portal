package com.portal.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;
    private final String token;

    // Constructor for authenticated users
    public JwtAuthenticationToken(UserDetails principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
        super.setAuthenticated(true); // ✅ Proper way to set authentication
    }

    // Constructor for unauthenticated requests
    public JwtAuthenticationToken(String token) {
        super(null);
        this.principal = null;
        this.token = token;
        setAuthenticated(false); // ✅ Mark as unauthenticated
    }

    @Override
    public Object getCredentials() {
        return token; // ✅ The JWT token itself is the "credential"
    }

    @Override
    public Object getPrincipal() {
        return principal; // ✅ The authenticated user details
    }
}
