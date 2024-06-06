package com.ila.hr.common;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    int getId();

    Identity getIdentity();
}
