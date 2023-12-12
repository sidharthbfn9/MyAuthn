package com.authn.JwtBasedAuthentication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class JwtRequest {
    private String email;
    private String password;
    private String appId;
}
