package com.medisync.medisync_service.pojo;

import org.springframework.web.multipart.MultipartFile;

public record CreateMedicalNoteRequest (
        MultipartFile audioFile,
        Long patientId
) {}