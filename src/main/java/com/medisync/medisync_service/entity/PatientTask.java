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
@Table(name = "patient_task")
@ToString
@SQLDelete(sql = "update patient_task set is_archived = true where id = ?")
@SQLRestriction("is_archived = false")
public class PatientTask extends CommonFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "audio_file_url")
    private String audioFileUrl;

    @Column(name = "transcription_text")
    private String transcriptionText;

    @Column(name = "summary_text")
    private String summaryText;
}