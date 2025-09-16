package week5.assignmentproblems;

import java.time.LocalDate;
import java.util.*;

final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory,
                         LocalDate birthDate, String bloodType) {
        if (recordId == null || patientDNA == null || birthDate == null || bloodType == null)
            throw new IllegalArgumentException("Invalid medical record data");
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = allergies != null ? allergies.clone() : new String[0];
        this.medicalHistory = medicalHistory != null ? medicalHistory.clone() : new String[0];
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return allergies.clone(); }
    public String[] getMedicalHistory() { return medicalHistory.clone(); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }
    public final boolean isAllergicTo(String substance) {
        for (String a : allergies) if (a.equalsIgnoreCase(substance)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" + "recordId='" + recordId + '\'' +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;
        MedicalRecord that = (MedicalRecord) o;
        return Objects.equals(recordId, that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }
}

class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;
    private int roomNumber;
    private String attendingPhysician;

    public Patient(String patientId, MedicalRecord record) {
        if (patientId == null || record == null) throw new IllegalArgumentException("Invalid patient data");
        this.patientId = patientId;
        this.medicalRecord = record;
    }

    public Patient(String patientId, MedicalRecord record, String currentName, String emergencyContact,
                   String insuranceInfo, int roomNumber, String attendingPhysician) {
        this(patientId, record);
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
    }

    String getBasicInfo() {
        return currentName + " - Room: " + roomNumber;
    }

    public String getPublicInfo() {
        return "Patient{name='" + currentName + "', room=" + roomNumber + "}";
    }

    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public String getCurrentName() { return currentName; }
    public void setCurrentName(String name) { this.currentName = name; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String contact) { this.emergencyContact = contact; }
    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String info) { this.insuranceInfo = info; }
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int number) { this.roomNumber = number; }
    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String physician) { this.attendingPhysician = physician; }

    @Override
    public String toString() {
        return "Patient{" + "id='" + patientId + '\'' +
                ", name='" + currentName + '\'' +
                ", room=" + roomNumber + '}';
    }
}

class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certs) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = new HashSet<>(certs);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return new HashSet<>(certifications); }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> quals) {
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = new ArrayList<>(quals);
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return new ArrayList<>(qualifications); }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> perms) {
        this.adminId = adminId;
        this.accessPermissions = new ArrayList<>(perms);
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return new ArrayList<>(accessPermissions); }
}

class HospitalSystem {
    private final Map<String, Object> patientRegistry = new HashMap<>();
    static final String PRIVACY_POLICY = "HIPAA-COMPLIANT";
    static final int MAX_PATIENTS = 1000;

    public boolean admitPatient(Object patient, Object staff) {
        if (!(patient instanceof Patient)) return false;
        if (!validateStaffAccess(staff, patient)) return false;
        patientRegistry.put(((Patient) patient).getPatientId(), patient);
        return true;
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        if (staff instanceof Doctor) return true;
        if (staff instanceof Nurse) return true;
        if (staff instanceof Administrator) return true;
        return false;
    }
}
