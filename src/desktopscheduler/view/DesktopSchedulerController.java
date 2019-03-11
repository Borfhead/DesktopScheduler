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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class DesktopSchedulerController implements Initializable {

    @FXML MenuItem addCustomerItem;
    @FXML MenuItem modifyCustomerItem;
    @FXML MenuItem generateReportItem;
    @FXML MenuBar menuBar;
    
    @FXML
    private void addCustomerSelected(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        showNewScene(root, "Add Customer");
    }
    
    @FXML
    private void modifyCustomerSelected(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModifyCustomer.fxml"));
        showNewScene(root, "Modify/Delete Customer");
    }
    
    @FXML
    private void generateReportSelected(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("GenerateReport.fxml"));
        showNewScene(root, "Reports");
    }
    
    //Temporary test method.  Delete later
    @FXML
    private void testPushed(ActionEvent event){
        
    }
    
    public static void showNewScene(Parent root, String title){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.showAndWait();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
