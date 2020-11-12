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

    public Doctor(int id, String password, String name, int permissions) {
        setId(id);
        setPassword(password);
        setName(name);
        setPermissions(permissions);
    }
    
    @Override
    public String[] methodList() {
        
    }
    
}
