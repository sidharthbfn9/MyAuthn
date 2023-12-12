package com.authn.JwtBasedAuthentication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticateRequest {
    private String token;
    private String appId;
    private String emailId;
}
