/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import desktopscheduler.model.DBDriver;
import java.io.IOException;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */
public class LoginController implements Initializable {
    
    @FXML TextField userNameField;
    @FXML TextField passwordField;
    @FXML Button loginBtn;
    @FXML Button cancelBtn;
    
    ResourceBundle bundle;

    
    
    @FXML
    private void loginBtnPressed(ActionEvent event) throws IOException {
        String user = userNameField.getText();
        String pass = passwordField.getText();
        
        if(DBDriver.authenticate(user, pass)){
            Parent root = FXMLLoader.load(getClass().getResource("DesktopScheduler.fxml"));
            Scene scene = userNameField.getScene();
            Stage stage = (Stage)scene.getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
        }
        else{
            Alert authFail = new Alert(AlertType.ERROR);
            authFail.setTitle("");
            authFail.setHeaderText(bundle.getString("failedauth"));
            authFail.setContentText("");
            authFail.showAndWait();
        }
    }
    
    @FXML
    private void cancelButtonPressed(ActionEvent e){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = ResourceBundle.getBundle("desktopscheduler.view/Login", Locale.getDefault());
        userNameField.setPromptText(bundle.getString("username"));
        passwordField.setPromptText(bundle.getString("password"));
        loginBtn.setText(bundle.getString("login"));
        cancelBtn.setText(bundle.getString("cancel"));
        
    }    
    
}
