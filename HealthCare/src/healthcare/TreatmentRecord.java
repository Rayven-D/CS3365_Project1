/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

/**
 *
 * @author fract
 */
public class TreatmentRecord {
    
    private String appointment_date;
    private String reason_for_visit;
    private double weight;
    private double height;
    private double blood_pressure;
    private String treatment;
    private String prescription;
    private String visited_doctor;
    
    // No arguements (if needed)
    public TreatmentRecord() {
        this.appointment_date = null;
        this.reason_for_visit = null;
        this.weight = 0.0;
        this.height = 0.0;
        this.blood_pressure = 0.0;
        this.treatment = null;
        this.prescription = null;
        this.visited_doctor = null;
    }
    
    // Standard constructor
    public TreatmentRecord(String appointment_date, String reason_for_visit, double weight, double height, double blood_pressure, String treatment, String prescription, String visited_doctor) {
        this.appointment_date = appointment_date;
        this.reason_for_visit = reason_for_visit;
        this.weight = weight;
        this.height = height;
        this.blood_pressure = blood_pressure;
        this.treatment = treatment;
        this.prescription = prescription;
        this.visited_doctor = visited_doctor;
    }
    
    // Constructor to show current visit and "update" patient values as needed.
    public TreatmentRecord(TreatmentRecord prev) {
        this.appointment_date = null;
        this.reason_for_visit = null;
        this.weight = prev.getWeight();
        this.height = prev.getHeight();
        this.blood_pressure = prev.getBlood_pressure();
        this.treatment = null;
        this.prescription = null;
        this.visited_doctor = null;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getReason_for_visit() {
        return reason_for_visit;
    }

    public void setReason_for_visit(String reason_for_visit) {
        this.reason_for_visit = reason_for_visit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(double blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getVisited_doctor() {
        return visited_doctor;
    }

    public void setVisited_doctor(String visited_doctor) {
        this.visited_doctor = visited_doctor;
    }
    
    
    
}
