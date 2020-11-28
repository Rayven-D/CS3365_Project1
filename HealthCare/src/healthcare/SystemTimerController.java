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
import java.text.*;
/**
 *
 * @author rjder
 */
public class SystemTimerController implements Runnable{
    
    private int hour = -1;
    private SystemTimer stm;
    private Calendar cal;
    
    public SystemTimerController(){}
    
    public SystemTimerController(int h, SystemTimer stm, Calendar c){
        this.hour = h;
        this.stm = stm;
        this.cal = c;
    }
    
    public void run(){
        Database db = new Database();
        if(hour == 21){
            stm.generateDailyReport(db);
            
        }else if(hour == 20){
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
            
        }else if(hour == 2){
            System.out.println("Add a new day");
            ArrayList<User> userList = null;
            try{
                userList = db.getUsers();
            
                for(User u: userList){
                    if(u.getPermissions() == 2){
                        ArrayList<Day> days = db.getSingleAvailability(u.getId());
                        String str = days.get(0).getDate();

                        Date prevDate = new SimpleDateFormat("MM/dd/yyyy").parse(str);
                        if(prevDate.before(this.cal.getTime())){
                            days.remove(0);
                            Date lastDate = new SimpleDateFormat("MM/dd/yyyy").parse(days.get(days.size()-1).getDate());
                            cal.setTime(lastDate);
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            DateFormat form = new SimpleDateFormat("MM/dd/yyyy");
                            String newDate = form.format(cal.getTime());
                            int []tempTimes = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                            
                            Day newDay = new Day(newDate, tempTimes);
                            days.add(newDay);
                            
                            db.saveSingleAvailability(u.getId(), days);
                            cal.setTime(new Date());
                            
                        }
                    }
                }
            }catch(Exception e){e.printStackTrace();}
        }
    }
    
    public void setCalendar(Calendar c){
        this.cal = c;
    }

}
