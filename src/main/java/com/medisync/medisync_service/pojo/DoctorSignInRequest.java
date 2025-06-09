package com.medisync.medisync_service.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorSignInRequest extends DoctorCheckRequest {
    private String password;
}
