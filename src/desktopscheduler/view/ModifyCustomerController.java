/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.Customer;
import desktopscheduler.model.DBDriver;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class ModifyCustomerController implements Initializable {

    @FXML TableView customerTable;
    @FXML TableColumn nameColumn;
    @FXML TableColumn idColumn;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCustomerTable();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }    
    
    public void populateCustomerTable(){
        customerTable.setItems(FXCollections.observableArrayList(DBDriver.getCustomerList()));
    }
    
    @FXML
    private void deleteBtnPressed(ActionEvent e){
        Customer selected = (Customer)customerTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Are you sure you want to delete " +selected.getCustomerName()+ "?");
            
            Optional<ButtonType> btnPressed = alert.showAndWait();
            if(!btnPressed.isPresent()){
                
            }
            else if(btnPressed.get() == ButtonType.OK){
                DBDriver.removeCustomer(selected.getCustomerID());
                populateCustomerTable();
            }
            
            
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Please select a customer to delete");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void modifyBtnPressed(ActionEvent e) throws IOException{
        Customer selected = (Customer)customerTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCustomer.fxml"));
            Parent root = loader.load();
            loader.<EditCustomerController>getController().initSelectedCustomer(selected);
            DesktopSchedulerController.showNewScene(root, "Modify Customer");
            populateCustomerTable();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Please select a customer to modify");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cancelBtnPressed(ActionEvent e){
        Stage s = (Stage)customerTable.getScene().getWindow();
        s.close();
    }
    
    
    
}
