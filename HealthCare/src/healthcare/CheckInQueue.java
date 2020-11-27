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
    private ArrayList<Integer> nurseQueue;
    
    public CheckInQueue(){
        this.doc123 = new ArrayList<>();
        this.doc124 = new ArrayList<>();
        this.doc125 = new ArrayList<>();
        this.nurseQueue = new ArrayList<>();
    }
    
    public  void removeFromQueue(int docID, Integer patientID){
        
         if(docID == 123){
            this.doc123.remove(patientID);
        }else if(docID == 124){
            this.doc124.remove(patientID);
        }else if(docID == 125){
            this.doc125.remove(patientID);
        }else if(docID == 127){
            this.nurseQueue.remove(patientID);
        }   
    }
    
    public ArrayList<Integer> getDocQueue(int docID){
        
        if(docID == 123){
            return this.doc123;
        }else if(docID == 124){
            return this.doc124;
        }else if(docID == 125){
            return this.doc125;
        }else if(docID == 127){
            return this.nurseQueue;
        }
        return null;
    }
    
    public void addToQueue(int docID, int patientID){
        if(docID == 123){
            this.doc123.add(patientID);
            System.out.println(doc123.toString());
        }else if(docID == 124){
            this.doc124.add(patientID);
        }else if(docID == 125){
            this.doc125.add(patientID);
        }else
            this.nurseQueue.add(patientID);
    }
}
