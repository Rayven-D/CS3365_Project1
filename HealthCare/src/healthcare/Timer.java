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

    public Timer() {
        d = new Date();
        c = Calendar.getInstance();
        c.set(2020, 11, 1);
    }

    public String getTodayDate() {
        c.set(2020, 11, 1);
        DateFormat form = new SimpleDateFormat("MM/dd/yyyy");
        String todayDate = form.format(c.getTime());
        return todayDate;
    }

    public String getTime() {
        DateFormat form = new SimpleDateFormat("h:mm aa");
        String todayDate = form.format(d);
        return todayDate;
    }

    public static void main(String[] args) {
        Timer t = new Timer();
        System.out.println(t.getTodayDate());
        System.out.println(t.getTime());
    }

    public ArrayList<String> getTwoWeekList() {
        ArrayList<String> temp = new ArrayList<>();
        DateFormat form = new SimpleDateFormat("MM/dd/yyyy");
        Calendar tempCal = c;
        c.set(2020, 11, 1);
        Date tempDate = tempCal.getTime();
        for (int i = 0; i < 10; i++) {
            temp.add(form.format(tempDate));
            tempCal.add(Calendar.DATE, 1);
            tempDate = tempCal.getTime();
        }
        return temp;
    }

    public void generateDailyReport(Database db) {
        Report report = new Report();
        ArrayList<ReportInformation> allReportInformations = new ArrayList();
        report.setDate(getTodayDate());
        ArrayList<User> users = db.getUsers();
        for (User user : users) {
            if (user.getPermissions() == 2) {
                ArrayList<Day> days = db.getSingleAvailability(user.getId());
                ReportInformation reportInformation = new ReportInformation();
                reportInformation.setName(user.getName());
                for (Day day : days) {
                    if (day.getDate().equals(getTodayDate())) {
                        reportInformation.setPatientAmount(day.getPatientCount());
                        reportInformation.setAmountEarned(calculateEarned(day.getAvailabilityTimes(), db));
                    }
                }
                allReportInformations.add(reportInformation);
            }
        }
        report.setReports(allReportInformations);
        db.saveSingleReport(report, getTodayDate());
    }

    private float calculateEarned(int[] array, Database db) {
        float earned = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                int patientID = -array[i];
                PatientChart chart = db.getSingleChart(patientID);
                ArrayList<TreatmentRecord> treatments = chart.getTreatment_record_arr();
                for(TreatmentRecord treatment : treatments) {
                    if(treatment.getAppointment_date().equals(getTodayDate())){
                        earned+= chart.getPatient_payment().getAmount();
                    }
                }
            };
        }
        return earned;
    }
}
