package com.ila.hr.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    private Identity identity;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private Jwt getToken() {
        return (Jwt) (getAuthentication().getPrincipal());
    }

    @Override
    public int getId() {
        var jwt = getToken();
        return Integer.parseInt(jwt.getClaimAsString("identityId"));
    }

    public String getName() {
        var jwt = getToken();
        return jwt.getClaimAsString("preferred_username");
    }

    @Override
    public Identity getIdentity() {
        if (identity == null) {
            identity = new Identity();
            identity.id = getId();
            identity.name = getName();
        }
        return identity;
    }
}
