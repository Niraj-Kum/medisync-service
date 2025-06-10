package com.medisync.medisync_service.pojo;

import com.medisync.medisync_service.entity.MedicalNote;

import java.time.LocalDateTime;
import java.util.Date;

public record MedicalNoteResponseDTO(
        Integer id,
        String summary,
        String transcription,
        String audioUrl,
        Integer patientId,
        Integer doctorId,
        Date createdAt
) {
    public static MedicalNoteResponseDTO fromEntity(MedicalNote note) {
        return new MedicalNoteResponseDTO(
                note.getId(),
                note.getSummary(),
                note.getTranscription(),
                note.getAudioUrl(),
                note.getPatient().getId(),
                note.getDoctor().getId(),
                note.getCreatedDate()
        );
    }
}