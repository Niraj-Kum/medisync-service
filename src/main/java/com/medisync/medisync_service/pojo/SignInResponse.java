package com.medisync.medisync_service.pojo;

import lombok.Builder;

@Builder
public class SignInResponse {
    private Boolean isNewUser;
    private String mobile;
    private String email;
    private String username;
}
