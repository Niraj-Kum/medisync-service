package com.medisync.medisync_service.repositories;

import com.medisync.medisync_service.entity.MedicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Integer> {
    List<MedicalNote> findByPatientIdOrderByCreatedAtDesc(Integer patientId);
}