/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthr
 */
public class Day {

    Date date;
    private int[] availabilityTimes;

    public Day() {
        date = new Date();
        availabilityTimes = new int[11];
        Arrays.fill(availabilityTimes, 0);
    }

    public Day(Date date, int[] availabilityTimes) {
        this.date = date;
        this.availabilityTimes = availabilityTimes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDateFromString(String date) {
        try {
            Date tempDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
            this.date = tempDate;
        } catch (ParseException ex) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(this.date.getTime());
        return todayAsString;
    }

    public int[] getAvailabilityTimes() {
        return availabilityTimes;
    }

    public void setAvailabilityTimes(int[] availabilityTimes) {
        this.availabilityTimes = availabilityTimes;
    }

}
