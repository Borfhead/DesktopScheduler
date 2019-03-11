/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.Address;
import desktopscheduler.model.Customer;
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
public class EditCustomerController implements Initializable {

    @FXML private TextField nameField;
    @FXML private TextField address1Field;
    @FXML private TextField address2Field;
    @FXML private ComboBox cityComboBox;
    @FXML private TextField postalField;
    @FXML private TextField phoneField;
    private Customer selected;
    
    
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
            int addrId = selected.getAddressID();
            if(DBDriver.updateAddress(addrId, address1, address2, cityId, postal, phone)){
                if(DBDriver.updateCustomer(selected.getCustomerID(), name, addrId)){
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Success");
                    a.setHeaderText("Changes Saved Successfully");
                    a.setContentText("");
                    a.showAndWait();
                    closeWindow();
                }
                else{
                    makeAlert("Failed to update customer");
                }
            }
            else{
                makeAlert("Failed to update address");
            }
            
//            if(addrId == -1){
//                makeAlert("Woops... something went wrong");
//            }
//            else if(DBDriver.updateCustomer(selected.getCustomerID(), name, addrId)){
//                Alert a = new Alert(AlertType.INFORMATION);
//                a.setTitle("Success");
//                a.setHeaderText("Changes Saved Successfully");
//                a.setContentText("");
//                a.showAndWait();
//                closeWindow();
//            }
            
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
    }
    
    public void initSelectedCustomer(Customer selected){
        this.selected = selected;
        Address address = DBDriver.getAddress(selected.getAddressID());
        nameField.setText(selected.getCustomerName());
        address1Field.setText(address.getAddress());
        address2Field.setText(address.getAddress2());
        cityComboBox.getSelectionModel().select(address.getCityID() - 1);
        postalField.setText(address.getPostalCode());
        phoneField.setText(address.getPhone());
        
    }
}
