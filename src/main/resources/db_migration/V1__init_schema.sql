CREATE SCHEMA IF NOT EXISTS medisync_schema;

SET search_path TO medisync_schema;

CREATE TABLE doctor (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(512) UNIQUE NOT NULL,
    mobile VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(512) NOT NULL,
    specialization VARCHAR(100),
    license_number VARCHAR(50),
    bio TEXT
);

CREATE TABLE patient (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INTEGER,
    gender VARCHAR(10),
    medical_history TEXT
);

CREATE TABLE medical_note (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    doctor_id INTEGER REFERENCES doctor(id),
    patient_id INTEGER REFERENCES patient(id),
    audio_file_url TEXT,
    transcription_text TEXT,
    summary_text TEXT
);

CREATE TABLE patient_task (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    patient_id INTEGER REFERENCES patient(id),
    doctor_id INTEGER REFERENCES doctor(id),
    title VARCHAR(100),
    description TEXT,
    due_date DATE,
    status VARCHAR(20)
);

CREATE TABLE referral (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    sender_doctor_id INTEGER REFERENCES doctor(id),
    receiver_doctor_id INTEGER REFERENCES doctor(id),
    patient_id INTEGER REFERENCES patient(id),
    notes TEXT,
    status VARCHAR(20)
);

CREATE TABLE message (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    sender_id INTEGER REFERENCES doctor(id),
    receiver_id INTEGER REFERENCES doctor(id),
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
