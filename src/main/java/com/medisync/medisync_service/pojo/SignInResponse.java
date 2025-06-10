package com.medisync.medisync_service.pojo;

import lombok.Builder;

import java.util.Date;

@Builder
public class SignInResponse {
    private Boolean isNewUser;
    private String mobile;
    private String email;
    private String username;
    private Date loggedInTime;
}
