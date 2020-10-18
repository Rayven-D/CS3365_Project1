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
public class AppointmentInformation {
    private long appointmentId;
    private String doctorName;
    private String date;
    private String time;
    private PatientChart chart;
    private long active;

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PatientChart getChart() {
        return chart;
    }

    public void setChart(PatientChart chart) {
        this.chart = chart;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }
}
