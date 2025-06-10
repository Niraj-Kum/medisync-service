package com.medisync.medisync_service.pojo;

import java.util.Date;

public record CreateOrUpdatePatientDTO(
        String name,
        String gender,
        Date dob,
        String phone,
        String email
) {}
