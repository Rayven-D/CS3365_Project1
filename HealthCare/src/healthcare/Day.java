/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Arthr
 */
public class Day {
    private String date;
    private int[] availabilityTimes;
    private int patientCount;

    public Day() {
    }

    public Day(String date, int[] availabilityTimes) {
        this.date = date;
        this.availabilityTimes = availabilityTimes;
    }

    public int[] getAvailabilityTimes() {
        return availabilityTimes;
    }

    public void setAvailabilityTimes(int[] availabilityTimes) {
        this.availabilityTimes = availabilityTimes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }
  
    @Override
    public String toString() {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date date = new Date();
        String todayAsString = df.format(date.getTime());
        return todayAsString;
    }
}
