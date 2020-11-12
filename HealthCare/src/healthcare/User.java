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
abstract class User {
    private int id;
    private String password;
    private String name;
    private int permissions;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }
    
    public static User createUser(int new_id, String new_password, String new_name, int new_permissions){
        User new_user = null;
        switch(new_permissions) {
            case 0: //Staff
                new_user = new Staff(new_id, new_password, new_name, new_permissions);
                break;
            case 1: //Nurse
                new_user = new Nurse(new_id, new_password, new_name, new_permissions);
                break;
            case 2: //Doctor
                new_user = new Doctor(new_id, new_password, new_name, new_permissions);
                break;
            case 3: //CEO
                new_user = new Ceo(new_id, new_password, new_name, new_permissions);
                break;
            default:
                System.out.println("ERROR: invalid permission level");
                System.exit(-1);
        }
        return new_user;
    }
    
    public abstract String[] methodList();
}
