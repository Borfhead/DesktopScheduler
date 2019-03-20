/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.Appointment;
import desktopscheduler.model.Customer;
import desktopscheduler.model.DBDriver;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class ModifyAppointmentController implements Initializable {
    @FXML Label dateLabel;
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
    @FXML TextField startHourField, startMinuteField, endHourField, endMinuteField;
    @FXML RadioButton startAM, startPM, endAM, endPM;
    private ToggleGroup startToggle, endToggle;
    private LocalDate apptDate;
    private LocalTime start, end;
    private Appointment selectedAppointment;
    private ArrayList<Customer> customerList;
    
    
    public void initAppointmentDate(LocalDate apptDate){
        this.apptDate = apptDate;
        dateLabel.setText("Date: " +apptDate.toString());
    }
    
    public void initSelectedAppointment(Appointment selected){
        selectedAppointment = selected;
        titleField.setText(selected.getTitle());
        typeField.setText(selected.getType());
        descField.setText(selected.getDescription());
        locationField.setText(selected.getLocation());
        contactField.setText(selected.getContact());
        urlField.setText(selected.getUrl());
        for(Customer c : customerList){
            if(selected.getCustomerID() == c.getCustomerID()){
                customerTable.getSelectionModel().select(c);
            }
        }
        String start = selectedAppointment.getStart().split(" ")[1];
        String end = selectedAppointment.getEnd().split(" ")[1];
        String startH = start.split(":")[0];
        String startM = start.split(":")[1];
        String endH = end.split(":")[0];
        String endM = end.split(":")[1];
        if(Integer.parseInt(startH) > 12){
            int i = Integer.parseInt(startH) - 12;
            if(i < 10){
                startH = "0" + Integer.toString(i);
            }
            else{
                startH = Integer.toString(i);
            }
            startToggle.selectToggle(startPM);
            
        }
        if(Integer.parseInt(endH) > 12){
            int i = Integer.parseInt(endH) - 12;
            if(i < 10){
                endH = "0" + Integer.toString(i);
            }
            else{
                endH = Integer.toString(i);
            }
            endToggle.selectToggle(endPM);
        }
        startHourField.setText(startH);
        startMinuteField.setText(startM);
        endHourField.setText(endH);
        endMinuteField.setText(endM);
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
        else if(!allTimesValid()){
            //Alerts made in allTimesValid method
        }
        else{
            int appointmentId = selectedAppointment.getApptID();
            int customerId = selected.getCustomerID();
            String title = titleField.getText();
            String description = descField.getText();
            String location = locationField.getText();
            String contact = contactField.getText();
            String type = typeField.getText();
            String url = urlField.getText();
            String startString = apptDate.toString() +" "+ start.toString()+ ":00";
            String endString = apptDate.toString() +" "+ end.toString()+ ":00";
            if(DBDriver.updateAppointment(appointmentId, customerId, title, description, location, contact, type, url, startString, endString)){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success");
                a.setHeaderText("Appointment Updated Successfully");
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
    
    private void addHourListener(TextField field){
        field.textProperty().addListener((ObservableValue<? extends String> obs, String oldString, String newString) ->{
        if(!newString.matches("\\d{0,9}")){
            field.setText(oldString);
            newString = oldString;
            }
        else if(newString.length() > 2){
            field.setText(oldString);
            newString = oldString;
        }
        try{
            if(Integer.parseInt(newString) > 12){
                field.setText("12");
            }
        }
        catch(NumberFormatException e){
            //Do nothing
        }
        });
    }
    
    private void addMinuteListener(TextField field){
        field.textProperty().addListener((ObservableValue<? extends String> obs, String oldString, String newString) ->{
        if(!newString.matches("\\d{0,9}")){
            field.setText(oldString);
            newString = oldString;
            }
        else if(newString.length() > 2){
            field.setText(oldString);
            newString = oldString;
        }
        try{
            if(Integer.parseInt(newString) >= 60){
                field.setText("59");
            }
        }
        catch(NumberFormatException e){
            //Nothing happens
        }
        
        });
    }
    
    private boolean allTimesValid(){
        String startHour = startHourField.getText();
        String startMinute = startMinuteField.getText();
        String endHour = endHourField.getText();
        String endMinute = endMinuteField.getText();
        RadioButton btn1 = (RadioButton)startToggle.getSelectedToggle();
        RadioButton btn2 = (RadioButton)endToggle.getSelectedToggle();
        String startParse = startHour +":"+ startMinute +":00 "+ btn1.getText();
        String endParse = endHour +":"+ endMinute +":00 "+ btn2.getText();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a", Locale.getDefault());
        try{
            start = LocalTime.parse(startParse, format);
            end = LocalTime.parse(endParse, format);
            if(start.isAfter(end)){
                makeAlert("Start time cannot be after end time");
                return false;
            }
            else if(start.isBefore(LocalTime.of(8, 0)) || start.isAfter(LocalTime.of(17, 0))){
                makeAlert("Start time is outside of business hours (8-5)");
            }
            else if(end.isBefore(LocalTime.of(8, 0)) || end.isAfter(LocalTime.of(17, 0))){
                makeAlert("End time is outside of business hours (8-5)");
            }
            else if(!hasConflict()){
                return true;
            }
        }
        catch(Exception e){
            makeAlert("Please enter time in hh:mm format");
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    private boolean hasConflict(){
        ArrayList<Appointment> otherAppts = DBDriver.getAppointmentList(apptDate);
        for(Appointment a : otherAppts){
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.getDefault());
            LocalTime tempStart = LocalDateTime.parse(a.getStart(), f).toLocalTime();
            LocalTime tempEnd = LocalDateTime.parse(a.getEnd(), f).toLocalTime();
            if(a.getApptID() != selectedAppointment.getApptID()){
                if(start.isBefore(tempStart) && end.isAfter(tempStart)){
                    makeAlert("Selected time conflicts with another appointment");
                return true;
                }
                else if(start.isAfter(tempStart) && start.isBefore(tempEnd)){
                    makeAlert("Selected time conflicts with another appointment");
                    return true;
                }
            }
            
        }
        return false;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = DBDriver.getCustomerList();
        customerTable.setItems(FXCollections.observableArrayList(customerList));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        startToggle = new ToggleGroup();
        endToggle = new ToggleGroup();
        startAM.setToggleGroup(startToggle);
        startPM.setToggleGroup(startToggle);
        endAM.setToggleGroup(endToggle);
        endPM.setToggleGroup(endToggle);
        startToggle.selectToggle(startAM);
        endToggle.selectToggle(endAM);
        
        addHourListener(startHourField);
        addMinuteListener(startMinuteField);
        addHourListener(endHourField);
        addMinuteListener(endMinuteField);
    }    
    
}
