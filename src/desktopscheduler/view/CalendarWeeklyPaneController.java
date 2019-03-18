/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.DBDriver;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class CalendarWeeklyPaneController implements Initializable {

    @FXML TableView mondayTable, tuesdayTable, wednesdayTable, thursdayTable, fridayTable;
    @FXML Button backButton, forwardButton;
    @FXML Label weekLabel, mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel;
    @FXML TableColumn titleColumn, titleColumn1, titleColumn2, titleColumn3, titleColumn4;
    private LocalDate monday, tuesday, wednesday, thursday, friday;
    
    private void populateAppointments(){
        mondayTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentList(monday)));
        tuesdayTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentList(tuesday)));
        wednesdayTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentList(wednesday)));
        thursdayTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentList(thursday)));
        fridayTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentList(friday)));
    }
    
    private void updateLabels(){
        String start = monday.getMonth().toString() +" "+ monday.getDayOfMonth() +", " +monday.getYear();
        String end = friday.getMonth().toString() +" "+ friday.getDayOfMonth() +", " +friday.getYear();
        weekLabel.setText(start+ " - " +end);
        
        mondayLabel.setText("Monday " +monday.getMonthValue()+ "/" +monday.getDayOfMonth());
        tuesdayLabel.setText("Tuesday " +tuesday.getMonthValue()+ "/" +tuesday.getDayOfMonth());
        wednesdayLabel.setText("Wednesday " +wednesday.getMonthValue()+ "/" +wednesday.getDayOfMonth());
        thursdayLabel.setText("Thursday " +thursday.getMonthValue()+ "/" +thursday.getDayOfMonth());
        fridayLabel.setText("Friday " +friday.getMonthValue()+ "/" +friday.getDayOfMonth());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monday = LocalDate.now().with(DayOfWeek.MONDAY);
        tuesday = LocalDate.now().with(DayOfWeek.TUESDAY);
        wednesday = LocalDate.now().with(DayOfWeek.WEDNESDAY);
        thursday = LocalDate.now().with(DayOfWeek.THURSDAY);
        friday = LocalDate.now().with(DayOfWeek.FRIDAY);
        mondayTable.setPlaceholder(new Label("No Apointments"));
        tuesdayTable.setPlaceholder(new Label("No Apointments"));
        wednesdayTable.setPlaceholder(new Label("No Apointments"));
        thursdayTable.setPlaceholder(new Label("No Apointments"));
        fridayTable.setPlaceholder(new Label("No Apointments"));
        
        backButton.setOnAction(event -> {
            monday = monday.minusWeeks(1);
            tuesday = tuesday.minusWeeks(1);
            wednesday = wednesday.minusWeeks(1);
            thursday = thursday.minusWeeks(1);
            friday = friday.minusWeeks(1);
            updateLabels();
            populateAppointments();
        });
        
        forwardButton.setOnAction(event -> {
            monday = monday.plusWeeks(1);
            tuesday = tuesday.plusWeeks(1);
            wednesday = wednesday.plusWeeks(1);
            thursday = thursday.plusWeeks(1);
            friday = friday.plusWeeks(1);
            updateLabels();
            populateAppointments();
        });
        
        updateLabels();
        populateAppointments();
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn1.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn3.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn4.setCellValueFactory(new PropertyValueFactory<>("title"));
        
    }    
    
}
