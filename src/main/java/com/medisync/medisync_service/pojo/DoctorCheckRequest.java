package com.medisync.medisync_service.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorCheckRequest {
    private String email;
    private String mobile;
}
