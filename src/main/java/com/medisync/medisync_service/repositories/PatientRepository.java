package com.medisync.medisync_service.repositories;

import com.medisync.medisync_service.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByDoctorEmailOrderByCreatedAtDesc(String doctorEmail);
}