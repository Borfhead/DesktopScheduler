/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.DBDriver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class AddCustomerController implements Initializable {

    @FXML TextField nameField;
    @FXML TextField address1Field;
    @FXML TextField address2Field;
    @FXML ComboBox cityComboBox;
    @FXML TextField postalField;
    @FXML TextField phoneField;
    
    
    @FXML
    private void submitBtnPressed(ActionEvent event){
        if(nameField.getText().equals("")){
            makeAlert("Please fill in the name field");
        }
        else if(address1Field.getText().equals("")){
            makeAlert("Please fill in the address 1 field");
        }
        else if(postalField.getText().equals("")){
            makeAlert("Please fill in the postal field");
        }
        else if(phoneField.getText().equals("")){
            makeAlert("Please fill in the phone field");
        }
        else{
            String name = nameField.getText();
            String address1 = address1Field.getText();
            String address2 = address2Field.getText();
            int cityId = cityComboBox.getSelectionModel().getSelectedIndex() + 1;
            String postal = postalField.getText();
            String phone = phoneField.getText();
            int newId = DBDriver.insertAddress(address1, address2, cityId, postal, phone);
            if(newId == -1){
                makeAlert("Woops... something went wrong");
            }
            else if(DBDriver.insertCustomer(name, newId)){
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Success");
                a.setHeaderText("Customer has been added successfully");
                a.setContentText("");
                a.showAndWait();
                closeWindow();
            }
            
        }
        
        
    }
    
    @FXML
    private void cancelBtnPressed(ActionEvent event){
        closeWindow();
    }
    
    private void closeWindow(){
        Stage s = (Stage)cityComboBox.getScene().getWindow();
        s.close();
    }
    
    private void makeAlert(String message){
        Alert newAlert = new Alert(AlertType.WARNING);
        newAlert.setTitle("Invalid");
        newAlert.setHeaderText(message);
        newAlert.setContentText("");
        newAlert.showAndWait();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cityComboBox.setItems(FXCollections.observableArrayList("New York, New York", "Phoenix, Arizona", "London, England"));
        cityComboBox.getSelectionModel().selectFirst();
    }    
}
