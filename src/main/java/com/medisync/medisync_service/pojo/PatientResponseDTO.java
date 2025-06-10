package com.medisync.medisync_service.pojo;

import com.medisync.medisync_service.entity.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record PatientResponseDTO(
        Integer id,
        String name,
        String gender,
        Date dob,
        String phone,
        String email,
        Integer doctorId,
        Date createdAt
) {
    public static PatientResponseDTO fromEntity(Patient p) {
        return new PatientResponseDTO(
                p.getId(), p.getName(), p.getGender(), p.getDob(),
                p.getMobile(), p.getEmail(),
                p.getDoctor().getId(), p.getCreatedDate()
        );
    }
}
