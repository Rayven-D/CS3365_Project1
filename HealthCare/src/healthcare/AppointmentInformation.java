package healthcare;

/**
 *
 * @author Arthr
 */
public class AppointmentInformation {

    private int appointmentId;
    private String doctorName;
    private String date;
    private String time;
    private PatientChart_Old chart;
    private int active;

    /**
     * Param Constructor
     *
     * @param appointmentId The special id for each appointment
     * @param doctorName The doctor who the patient selected name
     * @param date The date the appointment will take place mm/dd/yyyy format
     * @param time The time the appointment will take place
     * @param chart The chart object for this appointment
     * @param active If the appointment is active then it can still happen if it
     * isn't active then it is over
     */
    public AppointmentInformation(int appointmentId, String doctorName, String date, String time, PatientChart_Old chart, int active) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.chart = chart;
        this.active = active;
    }

    /**
     * Empty Constructor
     */
    public AppointmentInformation() {
    }

    /**
     * Get Appointment ID
     *
     * @return Will return the appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Set Appointment ID
     *
     * @param appointmentId The setter to the appointment ID
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Get Doctor Name
     *
     * @return The Doctor who the patient wanted
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Set Doctor Name
     *
     * @param doctorName Will set the doctors name
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * Get Date
     *
     * @return Will return the date string in a mm/dd/yyyy format
     */
    public String getDate() {
        return date;
    }

    /**
     * Set Date
     *
     * @param date A string that takes a date with a mm/dd/yyyy format
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get Time
     *
     * @return Will return time as a string. Place in AM/PM format for
     * consistency
     */
    public String getTime() {
        return time;
    }

    /**
     * Set Time
     *
     * @param time Time in a AM/PM format
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get Chart
     *
     * @return will return the Patient Chart object
     */
    public PatientChart_Old getChart() {
        return chart;
    }

    /**
     * Set Chart
     *
     * @param chart A PatientChart object
     */
    public void setChart(PatientChart_Old chart) {
        this.chart = chart;
    }

    /**
     * Get Active
     *
     * @return If the appointment is still active
     */
    public int getActive() {
        return active;
    }

    /**
     * Set Active
     *
     * @param active 0 or 1 if the appointment is active or not. 1 being active
     */
    public void setActive(int active) {
        this.active = active;
    }
}
