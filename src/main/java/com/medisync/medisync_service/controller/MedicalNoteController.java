package com.medisync.medisync_service.controller;

import com.medisync.medisync_service.pojo.MedicalNoteResponseDTO;
import com.medisync.medisync_service.service.MedicalNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class MedicalNoteController {

    private final MedicalNoteService noteService;

    public MedicalNoteController(MedicalNoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<MedicalNoteResponseDTO> uploadNote(
            @RequestParam("audioFile") MultipartFile audioFile,
            @RequestParam("patientId") Integer patientId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String doctorEmail = jwt.getClaimAsString("email");
        return ResponseEntity.ok(noteService.createNote(audioFile, patientId, doctorEmail));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<MedicalNoteResponseDTO>> getNotesForPatient(@PathVariable Integer id) {
        return ResponseEntity.ok(noteService.getNotesByPatient(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalNoteResponseDTO> getNote(@PathVariable Integer id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }
}

