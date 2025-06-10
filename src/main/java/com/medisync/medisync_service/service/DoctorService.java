package com.medisync.medisync_service.service;

import com.medisync.medisync_service.entity.Doctor;
import com.medisync.medisync_service.entity.Message;
import com.medisync.medisync_service.exception.BadRequestException;
import com.medisync.medisync_service.exception.InvalidCredentialsException;
import com.medisync.medisync_service.pojo.*;
import com.medisync.medisync_service.repositories.DoctorRepository;
import com.medisync.medisync_service.utils.constants.ResponseMessage;
import com.medisync.medisync_service.utils.utilities.ExcryptionDecryptionHandler;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Objects;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDTO getLoggedInDoctor(String email) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found with email: " + email));
        return DoctorDTO.fromEntity(doctor);
    }

    public MediSyncResponse<String> checkUserExistsByMobileOrEmail(DoctorCheckRequest request) {
        boolean doctorExists;
        if(Objects.nonNull(request.getEmail())) {
            String encryptedEmail = ExcryptionDecryptionHandler.encrypt(request.getEmail());
            doctorExists = doctorRepository.existsByEmail(encryptedEmail);
        } else {
            String encryptedMobile = ExcryptionDecryptionHandler.encrypt(request.getMobile());
            doctorExists = doctorRepository.existsByMobile(encryptedMobile);
        }
        return Boolean.TRUE.equals(doctorExists)? new MediSyncResponse<>(ResponseMessage.USER_EXISTS): new MediSyncResponse<>(ResponseMessage.USER_NOT_FOUND, true);
    }

    public SignInResponse verifyUserByMobileOrEmail(DoctorSignInRequest doctorSignInRequest) {
        if(Objects.isNull(doctorSignInRequest) || (StringUtils.isNotBlank(doctorSignInRequest.getEmail()) && StringUtils.isNotBlank(doctorSignInRequest.getMobile()))) {
            throw new BadRequestException(ResponseMessage.INVALID_MOBILE_OR_EMAIL);
        }
        Doctor doctor;
        if(Objects.nonNull(doctorSignInRequest.getEmail())) {
            String encryptedEmail = ExcryptionDecryptionHandler.encrypt(doctorSignInRequest.getEmail());
            doctor = doctorRepository.findByEmail(encryptedEmail).orElseThrow(() -> new InvalidCredentialsException(ResponseMessage.INVALID_CREDENTIALS));
        } else {
            String encryptedMobile = ExcryptionDecryptionHandler.encrypt(doctorSignInRequest.getMobile());
            doctor = doctorRepository.findByMobile(encryptedMobile).orElseThrow(() -> new InvalidCredentialsException(ResponseMessage.INVALID_CREDENTIALS));
        }
        String encryptedPassword = ExcryptionDecryptionHandler.encrypt(doctorSignInRequest.getPassword());
        if(!doctor.getPassword().equals(encryptedPassword)) {
            throw new InvalidCredentialsException(ResponseMessage.INVALID_CREDENTIALS);
        }
        return SignInResponse.builder().mobile(doctor.getMobile()).email(doctor.getEmail()).isNewUser(false).username(doctor.getUsername()).loggedInTime(new Date()).build();
    }

    public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorDTO.toEntity();
        doctor.setId(null);
        return DoctorDTO.fromEntity(doctorRepository.save(doctor));
    }

    public DoctorDTO updateDoctor(Integer id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(doctorDTO.name());
        doctor.setEmail(doctorDTO.email());
        doctor.setSpecialization(doctorDTO.specialization());
        doctor.setBio(doctorDTO.bio());

        return DoctorDTO.fromEntity(doctorRepository.save(doctor));
    }
}
