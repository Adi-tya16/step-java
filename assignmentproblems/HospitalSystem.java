package week3.assignmentproblems;

import java.util.*;

class Patient {
    String patientId, patientName, gender, contactInfo;
    int age;
    String[] medicalHistory;
    String[] currentTreatments;
    static int totalPatients = 0;
    private static int counter = 0;

    Patient(String name, int age, String gender, String contact, String[] history) {
        this.patientId = generateId();
        this.patientName = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contact;
        this.medicalHistory = history;
        this.currentTreatments = new String[5];
        totalPatients++;
    }

    private static String generateId() {
        counter++;
        return "P" + String.format("%03d", counter);
    }

    public void updateTreatment(String treatment) {
        for (int i = 0; i < currentTreatments.length; i++) {
            if (currentTreatments[i] == null) {
                currentTreatments[i] = treatment;
                break;
            }
        }
    }

    public void dischargePatient() {
        currentTreatments = new String[5];
        System.out.println(patientName + " discharged.");
    }
}

class Doctor {
    String doctorId, doctorName, specialization;
    String[] availableSlots;
    int patientsHandled;
    double consultationFee;
    private static int counter = 0;

    Doctor(String name, String specialization, double fee, String[] slots) {
        this.doctorId = generateId();
        this.doctorName = name;
        this.specialization = specialization;
        this.consultationFee = fee;
        this.availableSlots = slots;
        this.patientsHandled = 0;
    }

    private static String generateId() {
        counter++;
        return "D" + String.format("%03d", counter);
    }
}

class Appointment {
    String appointmentId, appointmentDate, appointmentTime, status, type;
    Patient patient;
    Doctor doctor;
    double billAmount;
    static int totalAppointments = 0;
    static double totalRevenue = 0;
    static String hospitalName = "CityCare Hospital";
    private static int counter = 0;

    Appointment(Patient p, Doctor d, String date, String time, String type) {
        this.appointmentId = generateId();
        this.patient = p;
        this.doctor = d;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.status = "Scheduled";
        this.type = type;
        d.patientsHandled++;
        totalAppointments++;
        generateBill();
    }

    private static String generateId() {
        counter++;
        return "A" + String.format("%03d", counter);
    }

    public void cancelAppointment() {
        status = "Cancelled";
        System.out.println("Appointment " + appointmentId + " cancelled.");
    }

    public void generateBill() {
        double rate = doctor.consultationFee;
        if (type.equalsIgnoreCase("Follow-up")) rate *= 0.5;
        if (type.equalsIgnoreCase("Emergency")) rate *= 2;
        this.billAmount = rate;
        totalRevenue += rate;
    }

    public void displayInfo() {
        System.out.println("Appointment " + appointmentId + ": " + patient.patientName +
                " with Dr. " + doctor.doctorName + " | " + type +
                " | Status: " + status + " | Bill: " + billAmount);
    }
}

public class HospitalSystem {
    public static void generateHospitalReport() {
        System.out.println("=== Hospital Report (" + Appointment.hospitalName + ") ===");
        System.out.println("Total Patients: " + Patient.totalPatients);
        System.out.println("Total Appointments: " + Appointment.totalAppointments);
        System.out.println("Total Revenue: " + Appointment.totalRevenue);
    }

    public static void getDoctorUtilization(Doctor[] doctors) {
        System.out.println("=== Doctor Utilization ===");
        for (Doctor d : doctors) {
            System.out.println(d.doctorName + " handled " + d.patientsHandled + " patients.");
        }
    }

    public static void getPatientStatistics(Patient[] patients) {
        System.out.println("=== Patient Statistics ===");
        for (Patient p : patients) {
            System.out.print(p.patientName + " | Treatments: ");
            for (String t : p.currentTreatments) if (t != null) System.out.print(t + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Doctor d1 = new Doctor("Alice", "Cardiologist", 1000, new String[]{"10AM", "2PM"});
        Doctor d2 = new Doctor("Bob", "Dermatologist", 800, new String[]{"11AM", "3PM"});

        Patient p1 = new Patient("John", 30, "Male", "9999999999", new String[]{"Diabetes"});
        Patient p2 = new Patient("Sara", 25, "Female", "8888888888", new String[]{"Asthma"});

        Appointment a1 = new Appointment(p1, d1, "2025-09-01", "10AM", "Consultation");
        Appointment a2 = new Appointment(p2, d2, "2025-09-01", "11AM", "Emergency");
        Appointment a3 = new Appointment(p1, d1, "2025-09-02", "2PM", "Follow-up");

        a1.displayInfo();
        a2.displayInfo();
        a3.displayInfo();

        p1.updateTreatment("Heart Checkup");
        p2.updateTreatment("Skin Allergy Treatment");

        generateHospitalReport();
        getDoctorUtilization(new Doctor[]{d1, d2});
        getPatientStatistics(new Patient[]{p1, p2});

        p2.dischargePatient();
    }
}

