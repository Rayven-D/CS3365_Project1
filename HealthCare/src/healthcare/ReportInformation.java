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
public class ReportInformation {
    private String name;
    private int patientAmount;
    private float amountEarned;

    public ReportInformation() {
    }

    public ReportInformation(String name, int patientAmount, float amountEarned) {
        this.name = name;
        this.patientAmount = patientAmount;
        this.amountEarned = amountEarned;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPatientAmount() {
        return patientAmount;
    }

    public void setPatientAmount(int patientAmount) {
        this.patientAmount = patientAmount;
    }

    public float getAmountEarned() {
        return amountEarned;
    }

    public void setAmountEarned(float amountEarned) {
        this.amountEarned = amountEarned;
    }
    
}
