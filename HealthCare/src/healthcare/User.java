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
public class User {

    private String firstName;
    private String lastName;
    private int userId;
    private String password;
    private int permissions;
    private String dob;
    private String address;
    private int phoneNumber;
    private int snn;
    private String healthInsurance;
    private String[] appointmentInformation = new String[5];
    private String[] paymentInformation = new String[4];

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSnn() {
        return snn;
    }

    public void setSnn(int snn) {
        this.snn = snn;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String[] getAppointmentInformation() {
        return appointmentInformation;
    }

    public void setAppointmentInformation(String[] appointmentInformation) {
        this.appointmentInformation = appointmentInformation;
    }

    public String[] getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(String[] paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

}
