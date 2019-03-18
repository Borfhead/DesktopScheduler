/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.Appointment;
import desktopscheduler.model.DBDriver;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class DesktopSchedulerController implements Initializable {

    @FXML private MenuItem addCustomerItem;
    @FXML private MenuItem modifyCustomerItem;
    @FXML private MenuItem generateReportItem;
    @FXML private MenuBar menuBar;
    @FXML private BorderPane mainPane;
    @FXML private RadioButton monthlyToggle;
    @FXML private RadioButton weeklyToggle;
    private ToggleGroup viewToggle;
    private AnchorPane weeklyPane;
    private AnchorPane monthlyPane;
    
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
    
    public static void showNewScene(Parent root, String title){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.showAndWait();
    }
    
    public void displayMonthly(){
        mainPane.setCenter(monthlyPane);
    }
    
    public void displayWeekly(){
        mainPane.setCenter(weeklyPane);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monthlyPane = new AnchorPane();
        weeklyPane = new AnchorPane();
        viewToggle = new ToggleGroup();
        monthlyToggle.setToggleGroup(viewToggle);
        weeklyToggle.setToggleGroup(viewToggle);
        
        monthlyToggle.selectedProperty().addListener(e -> {
            displayMonthly();
        });
        
        weeklyToggle.selectedProperty().addListener(e -> {
            displayWeekly();
        });
        
        try {
            monthlyPane = FXMLLoader.load(getClass().getResource("CalendarMonthlyPane.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            weeklyPane = FXMLLoader.load(getClass().getResource("CalendarWeeklyPane.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        displayMonthly();
    }
    
    
}
