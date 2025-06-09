package com.medisync.medisync_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "doctor")
@ToString
@SQLDelete(sql = "update doctor set is_archived = true where id = ?")
@SQLRestriction("is_archived = false")
public class Doctor extends CommonFields implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "specialization", nullable = false)
    private Integer specialization;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @Column(name = "bio", nullable = false)
    private String bio;
}
