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
@Table(name = "referral")
@ToString
@SQLDelete(sql = "update referral set is_archived = true where id = ?")
@SQLRestriction("is_archived = false")
public class Referral extends CommonFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sender_doctor_id", nullable = false)
    private Integer senderDoctorId;

    @Column(name = "receiver_doctor_id", nullable = false)
    private Integer receiverDoctorId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "notes", nullable = false)
    private String notes;

    @Column(name = "status", nullable = false)
    private String status;
}
