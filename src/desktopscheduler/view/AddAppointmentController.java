/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.Customer;
import desktopscheduler.model.DBDriver;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class AddAppointmentController implements Initializable {

    
    @FXML Label dateLabel;
    @FXML ComboBox startComboBox;
    @FXML ComboBox endComboBox;
    @FXML TableView customerTable;
    @FXML TableColumn nameColumn;
    @FXML TextField titleField;
    @FXML TextField typeField;
    @FXML TextArea descField;
    @FXML TextField locationField;
    @FXML TextField contactField;
    @FXML TextField urlField;
    @FXML Button addButton;
    @FXML Button cancelButton;
    private LocalDate apptDate;
    
    
    
    
    public void initAppointmentDate(LocalDate apptDate){
        this.apptDate = apptDate;
        dateLabel.setText("Date: " +apptDate.toString());
    }
    
    @FXML
    private void addButtonPressed(){
        Customer selected = (Customer)customerTable.getSelectionModel().getSelectedItem();
        if(titleField.getText().equals("")){
            makeAlert("Please fill the Title field");
        }
        else if(typeField.getText().equals("")){
            makeAlert("Please fill the Type field");
        }
        else if(selected == null){
            makeAlert("Please select a customer");
        }
        else{
            int customerId = selected.getCustomerID();
            String title = titleField.getText();
            String description = descField.getText();
            String location = locationField.getText();
            String contact = contactField.getText();
            String type = typeField.getText();
            String url = urlField.getText();
            String start = startComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String end = endComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            start = apptDate.toString() +" "+ start +":00";
            end = apptDate.toString() +" "+ end +":00";
            
            if(DBDriver.insertAppointment(customerId, title, description, location, contact, type, url, start, end)){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success");
                a.setHeaderText("Appointment Added Successfully");
                a.setContentText("");
                a.showAndWait();
                cancelButtonPressed();
            }
            
        }
    }
    
    @FXML
    private void cancelButtonPressed(){
        Stage s = (Stage)dateLabel.getScene().getWindow();
        s.close();
    }
    
    private void makeAlert(String message){
        Alert newAlert = new Alert(Alert.AlertType.WARNING);
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
        startComboBox.setItems(FXCollections.observableArrayList("9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"));
        endComboBox.setItems(FXCollections.observableArrayList("10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"));
        startComboBox.getSelectionModel().select(0);
        endComboBox.getSelectionModel().select(0);
        
        customerTable.setItems(FXCollections.observableArrayList(DBDriver.getCustomerList()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }    
    
}
