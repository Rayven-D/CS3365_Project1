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
public class Report {

    private String date;
    private ArrayList<ReportInformation> reportInformation;

    public Report() {
    }

    public Report(String date, ArrayList<ReportInformation> reportInformation) {
        this.date = date;
        this.reportInformation = reportInformation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ReportInformation> getReports() {
        return reportInformation;
    }

    public void setReports(ArrayList<ReportInformation> reports) {
        this.reportInformation = reports;
    }

}
