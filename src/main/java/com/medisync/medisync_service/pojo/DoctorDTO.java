package com.medisync.medisync_service.pojo;

import com.medisync.medisync_service.entity.Doctor;

public record DoctorDTO(Integer id, String name, String email, Integer specialization, String bio) {
    public static DoctorDTO fromEntity(Doctor doctor) {
        return new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getSpecialization(),
                doctor.getBio()
        );
    }

    public Doctor toEntity() {
        Doctor doctor = new Doctor();
        doctor.setId(this.id());
        doctor.setName(this.name());
        doctor.setEmail(this.email());
        doctor.setSpecialization(this.specialization());
        doctor.setBio(this.bio());
        return doctor;
    }
}
