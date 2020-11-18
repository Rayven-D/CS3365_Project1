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
public class Staff extends User {
    
    public Staff(int id, String password, String name, int permissions) {
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
        pat_info.add(patient.getPatient_payment());
        pat_info.trimToSize();
        return pat_info;
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
    
    //INSERT PATIENT_PAYMENT SETTERS HERE
}
