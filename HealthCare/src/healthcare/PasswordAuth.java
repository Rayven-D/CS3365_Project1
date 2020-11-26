/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;


import java.util.*;

/**
 *
 * @author Rayven
 */
public class PasswordAuth {
    private ArrayList<User> data;
    private User curUser;
    public PasswordAuth(){
        Database d = new Database();
        this.curUser = null;
        try{
            this.data = d.getUsers();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean validateInfo(String userID, String enteredPass){
        for(int i = 0 ; i < data.size(); i++){
            if(userID.equals(Integer.toString(data.get(i).getId()))){               
                curUser = data.get(i);         
                return curUser.getPassword().equals(enteredPass);
            }
        }
        return false;
    }
    
    
    public long userPermission(){
        return curUser.getPermissions();
    }
    
    public User allowUser(){
        int perms = curUser.getPermissions();
        switch(perms){
            case 0://staff
                return new Staff(curUser.getId(), curUser.getPassword(), curUser.getName(), curUser.getPermissions());
                
            case 1: //nurse
                return new Nurse(curUser.getId(), curUser.getPassword(), curUser.getName(), curUser.getPermissions());
                
            case 2://Doc
                return new Doctor(curUser.getId(), curUser.getPassword(), curUser.getName(), curUser.getPermissions());
            case 3://CEO
                return new Ceo(curUser.getId(), curUser.getPassword(), curUser.getName(), curUser.getPermissions());
            default://none
                System.exit(1);
                
        }
        return null;
    }
        
}
