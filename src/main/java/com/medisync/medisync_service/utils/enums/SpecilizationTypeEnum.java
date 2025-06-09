package com.medisync.medisync_service.utils.enums;

import java.util.Arrays;

public enum SpecilizationTypeEnum {

    CARDIOLOGIST(1, "Cardiologist"),
    NEUROLOGIST(2, "Neurologist"),
    DERMATOLOGIST(3, "Dermatologist"),
    ORTHOPEDIC_SURGEON(4, "Orthopedic Surgeon"),
    PEDIATRICIAN(5, "Pediatrician"),
    PSYCHIATRIST(6, "Psychiatrist"),
    GENERAL_PHYSICIAN(7, "General Physician"),
    ONCOLOGIST(8, "Oncologist"),
    ENDOCRINOLOGIST(9, "Endocrinologist"),
    ENT_SPECIALIST(10, "ENT Specialist"),
    GYNECOLOGIST(11, "Gynecologist"),
    UROLOGIST(12, "Urologist"),
    PULMONOLOGIST(13, "Pulmonologist"),
    NEPHROLOGIST(14, "Nephrologist"),
    GASTROENTEROLOGIST(15, "Gastroenterologist"),
    RADIOLOGIST(16, "Radiologist"),
    ANESTHESIOLOGIST(17, "Anesthesiologist");

    private final int code;
    private final String label;

    SpecilizationTypeEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static SpecilizationTypeEnum fromCode(int code) {
        return Arrays.stream(SpecilizationTypeEnum.values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid specialization code: " + code));
    }
}
