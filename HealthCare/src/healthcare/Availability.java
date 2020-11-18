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
public class Availability {
    private int userId;
    private ArrayList<Day> availabilityDates;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Day> getAvailabilityDates() {
        return availabilityDates;
    }

    public void setAvailabilityDates(ArrayList<Day> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }
    
}
