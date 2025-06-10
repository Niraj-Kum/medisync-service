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
@Table(name = "message")
@ToString
@SQLDelete(sql = "update message set is_archived = true where id = ?")
@SQLRestriction("is_archived = false")
public class Message extends CommonFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_doctor_id", nullable = false)
    private Doctor senderDoctor;

    @ManyToOne
    @JoinColumn(name = "recipient_doctor_id ", nullable = false)
    private Doctor recipientDoctor;

    @ManyToOne
    @JoinColumn(name = "sender_patient_id", nullable = false)
    private Patient senderPatient;

    @ManyToOne
    @JoinColumn(name = "recipient_patient_id", nullable = false)
    private Patient recipientPatient;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

}