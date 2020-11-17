/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.*;
import java.text.*;
import java.time.*;
/**
 *
 * @author Rayven
 */
public class Timer {
    Date d;
    Calendar c;
    public Timer(){
        d = new Date();
        c = Calendar.getInstance();
    }
    public String getTodayDate(){
        DateFormat form = new SimpleDateFormat("MM/dd/yy");
        String todayDate = form.format(d);
        return todayDate;
    }
    public String getTime(){
        DateFormat form = new SimpleDateFormat("h:mm aa");
        String todayDate = form.format(d);
        return todayDate;
    }
    public static void main(String [] args){
        Timer t = new Timer();
        System.out.println(t.getTodayDate());
        System.out.println(t.getTime());
    }
}
