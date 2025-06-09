package com.medisync.medisync_service.controller;

import com.medisync.medisync_service.pojo.DoctorDTO;
import com.medisync.medisync_service.pojo.DoctorSignInRequest;
import com.medisync.medisync_service.pojo.MediSyncResponse;
import com.medisync.medisync_service.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/me")
    public ResponseEntity<DoctorDTO> getLoggedInDoctor(@AuthenticationPrincipal Jwt principal) {
        String email = principal.getClaimAsString("email"); // or use "preferred_username"
        DoctorDTO doctorDTO = doctorService.getLoggedInDoctor(email);
        return ResponseEntity.ok(doctorDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<MediSyncResponse<String>> checkUserByEmailOrMobile(@RequestBody DoctorSignInRequest doctorSignInRequest) {
        MediSyncResponse<String> response = doctorService.checkUserExistsByMobileOrEmail(doctorSignInRequest);
        return new ResponseEntity<>(response, Boolean.TRUE.equals(response.getIsError())? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<DoctorDTO> verifyUserPassword(@RequestBody DoctorSignInRequest doctorSignInRequest) {
        DoctorDTO created = doctorService.verifyUserByMobileOrEmail(doctorSignInRequest);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/googleSignIn")
    public ResponseEntity<DoctorDTO> googleSignIn(@RequestBody DoctorDTO doctorDTO) {

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updated = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updated);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctorProfile(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO created = doctorService.addDoctor(doctorDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
