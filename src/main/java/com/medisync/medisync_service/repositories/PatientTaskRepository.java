package com.medisync.medisync_service.repositories;

import com.medisync.medisync_service.entity.PatientTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientTaskRepository extends JpaRepository<PatientTask, Integer> {

}
