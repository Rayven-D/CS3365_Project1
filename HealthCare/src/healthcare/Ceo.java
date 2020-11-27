/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;

/**
 *
 * @author fract
 */
public class Ceo extends User{
    
    public Ceo(int id, String password, String name, int permissions) {
        setId(id);
        setPassword(password);
        setName(name);
        setPermissions(permissions);
    }
    
    public ArrayList<Report> viewRepList(Database db) {
        return db.getReports();
    }
    
    public ArrayList getDailyReport(Report chosenRep) {
        ArrayList<Object> total_info = null;
        ArrayList<ReportInformation> rep_info = chosenRep.getReports();
        
        total_info.add(chosenRep.getDate());
        for(int i = 0; i < chosenRep.getReports().size(); i++) {
            total_info.add(rep_info.get(i).getName());
            total_info.add(rep_info.get(i).getPatientAmount());
            total_info.add(rep_info.get(i).getAmountEarned());
        }
        
        return total_info; // All info from all doctors on that day
    }
    
}
