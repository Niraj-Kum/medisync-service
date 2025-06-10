package com.medisync.medisync_service.entity;

import com.medisync.medisync_service.utils.enums.SpecilizationTypeEnum;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "referred_to", nullable = false)
    private SpecilizationTypeEnum referredTo;

    @ManyToOne
    @JoinColumn(name = "sender_doctor_id", nullable = false)
    private Doctor senderDoctor;

    @ManyToOne
    @JoinColumn(name = "receiver_doctor_id", nullable = false)
    private Doctor receiverDoctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "notes", nullable = false)
    private String notes;

}
