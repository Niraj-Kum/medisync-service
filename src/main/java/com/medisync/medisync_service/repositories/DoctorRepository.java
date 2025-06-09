package com.medisync.medisync_service.repositories;

import com.medisync.medisync_service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByEmail(String email);

    @Query("SELECT d FROM Doctor d WHERE email = :email OR mobile = :mobile")
    Optional<Doctor> findByEmailOrMobile(String email, String mobile);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END " +
            "FROM Doctor d WHERE d.email = :email OR d.mobile = :mobile")
    boolean existsByEmailOrMobile(@Param("email") String email, @Param("mobile") String mobile);

}