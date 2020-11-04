/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Rayven
 */
public class LoginInterface extends Application {
    
    
    Label usernameLabel,passwordLabel;
    TextField usernameTextField;
    PasswordField passwordTextField;
    Label errorMessage;
    Button login;
    HBox passwordBox, usernameBox;
    VBox loginInfo;
    @Override
    public void start(Stage primaryStage) {
        
        usernameLabel = new Label("Username: ");
        usernameTextField = new TextField("Last Name");
        usernameTextField.setOnAction(e->{
                handle(e);
        });
        
        passwordLabel = new Label("Password:  ");
        passwordTextField = new PasswordField();
        passwordTextField.setOnAction(e->{
                handle(e);
        });
                
        errorMessage = new Label("Username or Password is Incorrect.");
        
        login =new Button("Log In");
        login.setOnAction(e->{
                handle(e);
        });        
        
        usernameBox = new HBox();
        usernameBox.getChildren().addAll(usernameLabel, usernameTextField);
        usernameBox.setAlignment(Pos.CENTER);
        
        passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setAlignment(Pos.CENTER);
        
        loginInfo = new VBox();
        loginInfo.getChildren().addAll(usernameBox, passwordBox, login);
        loginInfo.setAlignment(Pos.CENTER);
        loginInfo.setSpacing(20);   
        
        Scene patientLogin = new Scene(loginInfo, 300, 300);
        
        
        
        primaryStage.setTitle("Login to Health Care System");
        primaryStage.setScene(patientLogin);
        primaryStage.show();
        
        
    }
   
    public void handle(ActionEvent e){
        PasswordAuth p = new PasswordAuth();
        loginInfo.getChildren().remove(errorMessage);
        if(usernameTextField.getText().equals("Last Name") || usernameTextField.getText().length()== 0 || !p.usernameFound(usernameTextField.getText())){
            loginInfo.getChildren().add(errorMessage);
        }else if(!p.passwordMatch(passwordTextField.getText())){
            loginInfo.getChildren().add(errorMessage);
        }else{
            switch((int)p.userPermission()){
                case 0: //customer
                    errorMessage.setText("Patient");
                    break;
                case 1: //staff
                    errorMessage.setText("Staff");
                    break;
                case 2: //Nurse
                    errorMessage.setText("Nurse");
                    break;
                case 3: //doctor
                    errorMessage.setText("Doctor");
                    break;
                case 4: //CEO
                    errorMessage.setText("CEO");
                    break;                                
            }
            loginInfo.getChildren().add(errorMessage);                            
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
