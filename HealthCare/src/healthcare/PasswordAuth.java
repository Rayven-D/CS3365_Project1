/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
            this.data = d.initDatabase();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean usernameFound(String username){
        for(int i = 0 ; i < data.size(); i++){
            if(username.equals(data.get(i).getName())){
                curUser = data.get(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean passwordMatch(String enteredPass){
        return curUser.getPassword().equals(enteredPass);
    }
    
    public long userPermission(){
        return curUser.getPermissions();
    }
        
}
