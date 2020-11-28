//CS 2365 OOP Spring 2020 Section 001
//Rayven Jan B. Deray

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;
import javafx.event.*;

/**
 *
 * @author rjder
 */
public class CEOController {

    private CEOInterface ceoInterface;
    Database db = new Database();

    public CEOController() {

    }

    public void viewReportsHandler(Event e) {
        System.out.println("hello");
    }

    public CEOController(CEOInterface ceoInterface) {
        this.ceoInterface = ceoInterface;
    }

    public ArrayList<Report> getReports() {
        ArrayList<Report> reports = new ArrayList();
        reports = db.getReports();
        return reports;
    }

    public void dateSelectorBoxHandler(Event e) {
        System.out.println(e);
    }

    public ArrayList<ReportInformation> getReportData(String date) {
        Report report = new Report();
        report = db.getSingleReport(date);
        return report.getReports();
    }

    public int getReportIndex(String date) {
        int value = 0;
        ArrayList<Report> reports = db.getReports();
        for (Report report : reports) {
            if (report.getDate().equals(date)) {
                break;
            }
            value++;
        }

        return value;
    }
    
    public void generateReport() {
        SystemTimer timer = new SystemTimer();
        timer.generateDailyReport(db);
    };
    public void handle(Event e) {
        /*
            Function of GUI goes here. what happens when certain aspects of the GUI is interacted with
            ->basically the  View Report use case
        
         */

    }

}
