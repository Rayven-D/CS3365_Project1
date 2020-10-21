package healthcare;

import java.util.ArrayList;

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
    private int SSN;
    private String healthInsurance;
    private int[] availabilityTimes;
    private ArrayList<AppointmentInformation> appointmentInformation = new ArrayList();
    private ArrayList<PaymentInformation> paymentInformation = new ArrayList();

    /**
     * Param Constructor
     *
     * @param firstName First Name of the user
     * @param lastName Last Name of the user
     * @param userId User's ID
     * @param password User's password
     * @param permissions User's permissions, any numbers can be picked here.
     * This will tell if it's an staff/doctor/patient
     * @param dob User's Date of Birth
     * @param address User's Address
     * @param phoneNumber User's Phone number
     * @param SSN User's SSN
     * @param healthInsurance User's Health insurance provider
     * @param availabilityTimes Array of availablityTimes, 0 being available and 1 being unavailable from 9am to 10pm each number is a 30minute increment
     */
    public User(String firstName, String lastName, int userId, String password, int permissions, String dob, String address, int phoneNumber, int SSN, String healthInsurance, int[] availabilityTimes) {
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
        this.availabilityTimes = availabilityTimes;
    }

    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * Get First name
     *
     * @return Returns the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set First name
     *
     * @param firstName New First name for user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get Last name
     *
     * @return Returns the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get Appointment Information
     *
     * @return An ArrayList of Appointments
     */
    public ArrayList<AppointmentInformation> getAppointmentInformation() {
        return appointmentInformation;
    }

    /**
     * Set Appointment Information
     *
     * @param appointmentInformation An ArrayList of appointments
     */
    public void setAppointmentInformation(ArrayList<AppointmentInformation> appointmentInformation) {
        this.appointmentInformation = appointmentInformation;
    }

    /**
     * Get Payment Information
     *
     * @return An ArrayList of payment informations
     */
    public ArrayList<PaymentInformation> getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Set Payment Information
     *
     * @param paymentInformation An ArrayList of payment informations
     */
    public void setPaymentInformation(ArrayList<PaymentInformation> paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    /**
     * Set Last name
     *
     * @param lastName New last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get User ID
     *
     * @return Will return the User's ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set User ID
     *
     * @param userId New User ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get Password
     *
     * @return The User's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     *
     * @param password A new Password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get Permissions
     *
     * @return The User's Permissions
     */
    public long getPermissions() {
        return permissions;
    }

    /**
     * Set Permissions
     *
     * @param permissions Set's a new persmission for the user
     */
    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    /**
     * Get DOB
     *
     * @return Will return the Date of Birth in a mm/dd/yyyy string format
     */
    public String getDob() {
        return dob;
    }

    /**
     * Set DOB
     *
     * @param dob Will set the Date of Birth, in mm/dd/yyyy format
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Get Address
     *
     * @return The User's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set Address
     *
     * @param address New User address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get Phone Number
     *
     * @return The User's phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set Phone number
     *
     * @param phoneNumber Set a new phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get SNN
     *
     * @return The User's SSN
     */
    public int getSSN() {
        return SSN;
    }

    /**
     * Set SNN
     *
     * @param snn New SSN for User
     */
    public void setSSN(int snn) {
        this.SSN = SSN;
    }

    /**
     * Get Health Insurance
     *
     * @return The User's Health Insurance
     */
    public String getHealthInsurance() {
        return healthInsurance;
    }

    /**
     * Set Health Insurance
     *
     * @param healthInsurance New Health Insurance for User
     */
    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    /**
     * Get Availability Times
     * @return An array containing available times, 0 being available and 1 being unavailable
     */
    public int[] getAvailabilityTimes() {
        return availabilityTimes;
    }

    /**
     * Set Availability times
     * @param availabilityTimes An Array of availability times, 0 being available and 1 being unavailable
     */
    public void setAvailabilityTimes(int[] availabilityTimes) {
        this.availabilityTimes = availabilityTimes;
    }
    
    
}
