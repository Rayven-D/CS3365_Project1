/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;

/**
 *
 * @author Arthr
 */
public class User {

    private String firstName;
    private String lastName;
    private long userId;
    private String password;
    private long permissions;
    private String dob;
    private String address;
    private long phoneNumber;
    private long SSN;
    private String healthInsurance;
    private ArrayList<AppointmentInformation> appointmentInformation = new ArrayList();
    private ArrayList<PaymentInformation> paymentInformation = new ArrayList();

    public User(String firstName, String lastName, long userId, String password, long permissions, String dob, String address, long phoneNumber, long SSN, String healthInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        this.permissions = permissions;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
        this.healthInsurance = healthInsurance;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<AppointmentInformation> getAppointmentInformation() {
        return appointmentInformation;
    }

    public void setAppointmentInformation(ArrayList<AppointmentInformation> appointmentInformation) {
        this.appointmentInformation = appointmentInformation;
    }

    public ArrayList<PaymentInformation> getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(ArrayList<PaymentInformation> paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPermissions() {
        return permissions;
    }

    public void setPermissions(long permissions) {
        this.permissions = permissions;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getSSN() {
        return SSN;
    }

    public void setSSN(long snn) {
        this.SSN = SSN;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }
}
