//CS 2365 OOP Spring 2020 Section 001
//Rayven Jan B. Deray

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.lang.*;
import java.util.*;
/**
 *
 * @author rjder
 */
public class SystemTimerController implements Runnable{
    
    private int hour = 0;
    private SystemTimer stm;
    
    public SystemTimerController(){}
    
    public SystemTimerController(int h, SystemTimer stm){
        this.hour = h;
        this.stm = stm;
    }
    
    public void run(){
        Database db = new Database();
        if(hour == 21){
            stm.generateDailyReport(db);
            
        }else if(hour == 20){
            System.out.println("This is where removing no-shows will go.");
            ArrayList<User> userList = null;
            try{
                userList = db.getUsers();
            }catch(Exception e){e.printStackTrace();}
            for(User u: userList){
                if(u.getPermissions() == 2){
                    ArrayList<Day> day = db.getSingleAvailability(u.getId());
                    for(Day d: day){
                        if(d.getDate().equals(stm.getTodayDate())){
                            int times[] = d.getAvailabilityTimes();
                            for(int i =0 ; i < times.length; i++){
                                if(times[i] > 0){
                                    times[i] = 0;
                                }
                            }
                            d.setAvailabilityTimes(times);
                        }
                    }
                    db.saveSingleAvailability(u.getId(), day);
                }
            }
            
        }
    }

}
