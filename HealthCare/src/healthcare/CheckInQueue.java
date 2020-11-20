/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;
/**
 *
 * @author Rayven
 */
public class CheckInQueue {
    private ArrayList<Integer> doc123;
    private ArrayList<Integer> doc124;
    private ArrayList<Integer> doc125;
    
    public CheckInQueue(){
        this.doc123 = new ArrayList<>();
        this.doc124 = new ArrayList<>();
        this.doc125 = new ArrayList<>();
    }
    
    public int getNextPatient(int docID){
        int patientID = 0;
        if(docID == 123){
            patientID = doc123.get(0);
            doc123.remove(0);
            
        }else if(docID == 124){
            patientID = doc124.get(0);
            doc124.remove(0);
        }else if(docID == 125){
            patientID = doc125.get(0);
            doc125.remove(0);
        }
        return patientID;
        
    }
    
    public void addToQueue(int docID, int patientID){
        if(docID == 123){
            this.doc123.add(patientID);
        }else if(docID == 124){
            this.doc124.add(patientID);
        }else if(docID == 125){
            this.doc125.add(patientID);
        }
    }
}
