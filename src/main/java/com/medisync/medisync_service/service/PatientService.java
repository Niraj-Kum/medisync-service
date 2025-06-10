package com.medisync.medisync_service.service;

import com.medisync.medisync_service.entity.Doctor;
import com.medisync.medisync_service.entity.Patient;
import com.medisync.medisync_service.pojo.CreateOrUpdatePatientDTO;
import com.medisync.medisync_service.pojo.PatientResponseDTO;
import com.medisync.medisync_service.repositories.DoctorRepository;
import com.medisync.medisync_service.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public PatientService(PatientRepository patientRepo, DoctorRepository doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public PatientResponseDTO create(CreateOrUpdatePatientDTO dto, String doctorEmail) {
        Doctor doctor = doctorRepo.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setGender(dto.gender());
        patient.setDob(dto.dob());
        patient.setMobile(dto.phone());
        patient.setEmail(dto.email());
        patient.setDoctor(doctor);

        return PatientResponseDTO.fromEntity(patientRepo.save(patient));
    }

    public List<PatientResponseDTO> getAll(String doctorEmail) {
        return patientRepo.findByDoctorEmailOrderByCreatedAtDesc(doctorEmail)
                .stream().map(PatientResponseDTO::fromEntity).toList();
    }

    public PatientResponseDTO getById(Integer id) {
        Patient p = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return PatientResponseDTO.fromEntity(p);
    }

    public PatientResponseDTO update(Integer id, CreateOrUpdatePatientDTO dto) {
        Patient p = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        p.setName(dto.name());
        p.setGender(dto.gender());
        p.setDob(dto.dob());
        p.setMobile(dto.phone());
        p.setEmail(dto.email());

        return PatientResponseDTO.fromEntity(patientRepo.save(p));
    }

    public void delete(Integer id) {
        patientRepo.deleteById(id);
    }
}
