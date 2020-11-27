//CS 2365 OOP Spring 2020 Section 001
//Rayven Jan B. Deray

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author rjder
 */
public class CEOInterface extends Application {
    
    Label dateLabel, numPatServedLabel, amountEarnedLabel;
    TextField dateField, numPatServedField, amountEarnedField;
    ComboBox docCombo;
    
    private CEOController controller;
    
    @Override
    public void start(Stage primaryStage) {
        this.controller = new CEOController(this); 
        
      
    }
    
    public void handle(Event e){
        this.controller.handle(e);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
