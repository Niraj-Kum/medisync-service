package com.medisync.medisync_service.service;

import com.medisync.medisync_service.entity.Doctor;
import com.medisync.medisync_service.entity.MedicalNote;
import com.medisync.medisync_service.entity.Patient;
import com.medisync.medisync_service.pojo.MedicalNoteResponseDTO;
import com.medisync.medisync_service.repositories.DoctorRepository;
import com.medisync.medisync_service.repositories.MedicalNoteRepository;
import com.medisync.medisync_service.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class MedicalNoteService {

    private final MedicalNoteRepository noteRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public MedicalNoteService(MedicalNoteRepository noteRepo,
                              DoctorRepository doctorRepo,
                              PatientRepository patientRepo) {
        this.noteRepo = noteRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public MedicalNoteResponseDTO createNote(MultipartFile audioFile, Integer patientId, String doctorEmail) {
        String audioUrl = saveAudio(audioFile);

        String transcription = transcribeAudio(audioFile);
        String summary = summarizeText(transcription);

        Doctor doctor = doctorRepo.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        MedicalNote note = new MedicalNote();
        note.setDoctor(doctor);
        note.setPatient(patient);
        note.setAudioUrl(audioUrl);
        note.setTranscription(transcription);
        note.setSummary(summary);

        MedicalNote saved = noteRepo.save(note);
        return MedicalNoteResponseDTO.fromEntity(saved);
    }

    public List<MedicalNoteResponseDTO> getNotesByPatient(Integer patientId) {
        return noteRepo.findByPatientIdOrderByCreatedAtDesc(patientId)
                .stream()
                .map(MedicalNoteResponseDTO::fromEntity)
                .toList();
    }

    public MedicalNoteResponseDTO getNoteById(Integer id) {
        MedicalNote note = noteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        return MedicalNoteResponseDTO.fromEntity(note);
    }

    private String saveAudio(MultipartFile file) {
        // TODO download audio
        return "https://mock.audio/" + UUID.randomUUID() + ".mp3";
    }

    private String transcribeAudio(MultipartFile file) {
        // TODO transcribe file
        return "Transcribed text from audio.";
    }

    private String summarizeText(String text) {
        // TODO summarize text
        return "Summary of transcription.";
    }
}

