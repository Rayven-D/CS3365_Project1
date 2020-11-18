/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.*;

/**
 *
 * @author fract
 */
public class Doctor extends User{
    
    private ArrayList<Day> availabilityDates;
    public Doctor(int id, String password, String name, int permissions) {
        setId(id);
        setPassword(password);
        setName(name);
        setPermissions(permissions);
    }
    
    public ArrayList readPatientChart(PatientChart patient) {
        ArrayList<Object> pat_info = null;
        pat_info.add(patient.getPatient_name());
        pat_info.add(patient.getBirthday());
        pat_info.add(patient.getAddress());
        pat_info.add(patient.getSsn());
        pat_info.add(patient.getInsurance());
        pat_info.add(patient.getPatient_id()); // Not editable
        pat_info.add(patient.getTreatment_record_arr()); //ArrayList of previous visit/treatments
        pat_info.add(patient.getCurrent_visit()); //Current treatmentRecord (starts off almost blank/ requires updating)
        pat_info.trimToSize();
        return pat_info;
    }

    public ArrayList<Day> getAvailabilityDates() {
        return availabilityDates;
    }

    public void setAvailabilityDates(ArrayList<Day> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }
    
    public void changePatient_name(PatientChart patient, String new_name) {
        patient.setPatient_name(new_name);
    }
    
    public void changeBirthday(PatientChart patient, String new_bday) {
        patient.setBirthday(new_bday);
    }
    
    public void changeAddr(PatientChart patient, String new_addr) {
        patient.setAddress(new_addr);
    }
    
    public void changeSsn(PatientChart patient, int new_ssn) {
        patient.setSsn(new_ssn);
    }
    
    public void changeInsurance(PatientChart patient, String new_insurance) {
        patient.setInsurance(new_insurance);
    }
    
    public void changeReason(PatientChart patient, String reason) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setReason_for_visit(reason);
    }
    
    public void changeWeight(PatientChart patient, double weight) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setWeight(weight);
    }
    
    public void changeHeight(PatientChart patient, double height) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setHeight(height);
    }
    
    public void changeBlood_Pressure(PatientChart patient, double bp) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setBlood_pressure(bp);
    }
    
    public void changeTreatment(PatientChart patient, String treat) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setTreatment(treat);
    }
    
    public void changePrescription(PatientChart patient, String prescript) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setPrescription(prescript);
    }
    
    public void changeVisited_Doc(PatientChart patient, String doc) {
        TreatmentRecord current = patient.getCurrent_visit();
        current.setVisited_doctor(doc);
    }
}
