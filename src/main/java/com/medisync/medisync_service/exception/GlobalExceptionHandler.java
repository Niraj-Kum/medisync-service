package com.medisync.medisync_service.exception;

import com.medisync.medisync_service.pojo.MediSyncResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MediSyncResponse<String>> handleValidationException(MethodArgumentNotValidException exception) {
        StringJoiner message = new StringJoiner(",");
        exception.getBindingResult().getAllErrors().forEach(objectError -> message.add(objectError.getDefaultMessage()));
        log.error("Exception : {}", message, exception);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MediSyncResponse<>(message.toString(), true));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MediSyncResponse<String>> handleBadRequestException(BadRequestException exception) {
        log.error("Exception : {}", exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MediSyncResponse<>(exception.getMessage(), true));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<MediSyncResponse<String>> handleInvalidCredentials(InvalidCredentialsException ex) {
        return new ResponseEntity<>(new MediSyncResponse<>(ex.getMessage(), true), HttpStatus.UNAUTHORIZED);
    }
}
