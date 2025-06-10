package com.medisync.medisync_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "medical_note")
@ToString
@SQLDelete(sql = "update medical_note set is_archived = true where id = ?")
@SQLRestriction("is_archived = false")
public class MedicalNote extends CommonFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "audio_file_url", nullable = false)
    private String audioUrl;

    @Lob
    @Column(name = "transcription_text", nullable = false)
    private String transcription;

    @Lob
    @Column(name = "summary_text", nullable = false)
    private String summary;

}