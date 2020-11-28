/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.*;
import java.text.*;
import java.time.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Rayven
 */
public class SystemTimer{
    Date d;
    Calendar c;
    public void run(){
        DateFormat form = new SimpleDateFormat("h");
        System.out.println(form.format(c.getTime()));
    }
    
    public SystemTimer(){
        d = new Date();
        c = Calendar.getInstance();
        c.set(2020, 11, 1);
        startReportScheduler();
        startNoShowScheduler();
    }
    public void startReportScheduler(){
        ZonedDateTime current = ZonedDateTime.now(ZoneId.of("America/Chicago"));
        ZonedDateTime nextRun = current.withHour(21).withMinute(0).withSecond(0);
        
        if(current.compareTo(nextRun) > 0)
            nextRun = nextRun.plusDays(1);
        Duration untilNextRun = Duration.between(current, nextRun);
        long initialDelay = untilNextRun.getSeconds();
        
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new SystemTimerController(21, this), initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
    public void startNoShowScheduler(){
        ZonedDateTime current = ZonedDateTime.now(ZoneId.of("America/Chicago"));
        ZonedDateTime nextRun = current.withHour(20).withMinute(0).withSecond(0);
        
        if(current.compareTo(nextRun) > 0)
            nextRun = nextRun.plusDays(1);
        Duration untilNextRun = Duration.between(current, nextRun);
        long initialDelay = untilNextRun.getSeconds();
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new SystemTimerController(20,this), initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
    
    public void forceCreateReport(){
        SystemTimerController stc = new SystemTimerController(21, this);
        stc.run();
    }
    public void forceRemoveNoShows(){
        SystemTimerController stc = new SystemTimerController(20, this);
        stc.run();
    }
    
    public String getTodayDate(){
        c.set(2020,11,1);
        DateFormat form = new SimpleDateFormat("MM/dd/yyyy");
        String todayDate = form.format(c.getTime());
        return todayDate;
    }
    public String getTime(){
        DateFormat form = new SimpleDateFormat("h:mm aa");
        String todayDate = form.format(c.getTime());
        return todayDate;
    }
    public void setTime(int n){
        c.set(Calendar.HOUR_OF_DAY, n);
        
    }    
    public ArrayList<String> getTwoWeekList(){
        ArrayList<String> temp = new ArrayList<>();
        DateFormat form = new SimpleDateFormat("MM/dd/yyyy");
        Calendar tempCal = c;
        c.set(2020, 11,1);
        Date tempDate = tempCal.getTime();
        for(int i = 0 ; i < 10; i++){
            temp.add(form.format(tempDate));
            tempCal.add(Calendar.DATE, 1);
            tempDate = tempCal.getTime();
        }
        return temp;
    }
    public static void main(String [] args){
        SystemTimer tm = new SystemTimer();
        System.out.println(tm.getTodayDate());
        System.out.println(tm.getTime());
        tm.setTime(12);
        System.out.println(tm.getTime());
        tm.forceRemoveNoShows();
    }
}
