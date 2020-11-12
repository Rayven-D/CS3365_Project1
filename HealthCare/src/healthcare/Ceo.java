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
public class Ceo extends User{
    
    public Ceo(int id, String password, String name, int permissions) {
        setId(id);
        setPassword(password);
        setName(name);
        setPermissions(permissions);
    }
}
