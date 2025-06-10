package com.medisync.medisync_service.controller;

import com.medisync.medisync_service.pojo.CreateOrUpdatePatientDTO;
import com.medisync.medisync_service.pojo.PatientResponseDTO;
import com.medisync.medisync_service.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @RequestBody CreateOrUpdatePatientDTO dto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String doctorEmail = jwt.getClaimAsString("email");
        return ResponseEntity.ok(patientService.create(dto, doctorEmail));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll(
            @AuthenticationPrincipal Jwt jwt
    ) {
        String doctorEmail = jwt.getClaimAsString("email");
        return ResponseEntity.ok(patientService.getAll(doctorEmail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdatePatientDTO dto
    ) {
        return ResponseEntity.ok(patientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
