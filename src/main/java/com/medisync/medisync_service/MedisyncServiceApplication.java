package com.medisync.medisync_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
public class MedisyncServiceApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(MedisyncServiceApplication.class, args);
	}

}
