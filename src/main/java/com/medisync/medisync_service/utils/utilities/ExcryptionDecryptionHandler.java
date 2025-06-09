package com.medisync.medisync_service.utils.utilities;

import com.medisync.medisync_service.exception.InvalidArgumentException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ExcryptionDecryptionHandler {

    @Value("${aes.secret.key}")
    private static String secretKey;

    private static final String ENCRYPTION_ALGO = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 16;
    private static final int IV_LENGTH = 12;

    private static SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, "AES");
    }

    public static String encrypt(String plainText) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), parameterSpec);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            byte[] encryptedWithIv = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, encryptedWithIv, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(encryptedWithIv);
        } catch (Exception e) {
            throw new InvalidArgumentException("Error while Encrypting data : " + e.getMessage());
        }
    }

    public static String decrypt(String encrypted) {
        try {
            byte[] encryptedBytesWithIv = Base64.getDecoder().decode(encrypted);
            byte[] iv = new byte[IV_LENGTH];
            byte[] encryptedBytes = new byte[encryptedBytesWithIv.length - IV_LENGTH];

            System.arraycopy(encryptedBytesWithIv, 0, iv, 0, IV_LENGTH);
            System.arraycopy(encryptedBytesWithIv, IV_LENGTH, encryptedBytes, 0, encryptedBytes.length);

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), parameterSpec);

            byte[] decrypted = cipher.doFinal(encryptedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new InvalidArgumentException("Error while Decrypting data : " + e.getMessage());
        }
    }
}