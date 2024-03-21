package com.insta.instagram.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenClaims {
    private String username;
}
