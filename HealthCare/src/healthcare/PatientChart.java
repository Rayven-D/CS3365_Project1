/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

/**
 *
 * @author Arthr
 */
public class PatientChart {

    private float weight;
    private float height;
    private float bloodPressure;
    private String reason;
    private String treatment;
    private String prescription;

    public PatientChart(int weight, int height, int bloodPressure, String reason, int patientId, String treatment, String prescription) {
        this.weight = weight;
        this.height = height;
        this.bloodPressure = bloodPressure;
        this.reason = reason;
        this.treatment = treatment;
        this.prescription = prescription;
    }

    public PatientChart() {
    }
    
    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public float getWeight() {
        return weight;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
