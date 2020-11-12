/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

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
    
}
