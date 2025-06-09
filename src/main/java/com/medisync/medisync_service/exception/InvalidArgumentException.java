package com.medisync.medisync_service.exception;


import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class InvalidArgumentException  extends IllegalArgumentException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
