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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class CalendarMonthlyPaneController implements Initializable {
    
    @FXML private GridPane calendarPane;
    @FXML private VBox appointmentPane;
    @FXML Label monthLabel;
    @FXML Button backButton;
    @FXML Button forwardButton;
    @FXML private Button addApptButton;
    @FXML private Button modifyApptButton;
    @FXML private Button deleteButton;
    @FXML private TableView apptTable;
    @FXML private TableColumn titleColumn;
    @FXML private TableColumn startColumn;
    @FXML private TableColumn endColumn;
    
    private ToggleGroup dayGroup;
    private LocalDate selectedDate;
    private YearMonth selectedMonth;
    
    
    private void populateAppointments(){
        apptTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentList(selectedDate)));
    }
    
    @FXML
    private void addButtonPressed() throws IOException{
        DayInMonth apptDate = (DayInMonth)dayGroup.getSelectedToggle();
        if(apptDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Please select a date");
            alert.setContentText("");
            alert.showAndWait();
        }
        else if(apptDate.getDate().getDayOfWeek().getValue() > 5){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Cannot schedule appointments on weekends");
            alert.setContentText("");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointment.fxml"));
            Parent root = loader.load();
            loader.<AddAppointmentController>getController().initAppointmentDate(apptDate.getDate());
            DesktopSchedulerController.showNewScene(root, "Add Appointment");
            populateAppointments();
        }
    }
    
    @FXML
    private void deleteButtonPressed(){
        Appointment selected = (Appointment)apptTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Are you sure you want to delete this appointment?");
            
            Optional<ButtonType> btnPressed = alert.showAndWait();
            if(!btnPressed.isPresent()){
                
            }
            else if(btnPressed.get() == ButtonType.OK){
                DBDriver.removeAppointment(selected.getApptID());
                populateAppointments();
            }
    }
}
    
    @FXML
    private void modifyButtonPressed() throws IOException{
        Appointment selected = (Appointment)apptTable.getSelectionModel().getSelectedItem();
        DayInMonth apptDate = (DayInMonth)dayGroup.getSelectedToggle();
        if(selected != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAppointment.fxml"));
            Parent root = loader.load();
            loader.<ModifyAppointmentController>getController().initAppointmentDate(apptDate.getDate());
            loader.<ModifyAppointmentController>getController().initSelectedAppointment(selected);
            DesktopSchedulerController.showNewScene(root, "Modify Appointment");
            populateAppointments();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Please select an appointment to modify");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    
    private void populateGrid(){
        monthLabel.setText(selectedMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())
        +" "+ Integer.toString(selectedMonth.getYear()));
        LocalDate first = selectedMonth.atDay(1);
        int startCol = 0;
        switch(first.getDayOfWeek().toString()){
            case "SUNDAY":
                startCol = 0;
                break;
            case "MONDAY":
                startCol = 1;
                break;
            case "TUESDAY":
                startCol = 2;
                break;
            case "WEDNESDAY":
                startCol = 3;
                break;
            case "THURSDAY":
                startCol = 4;
                break;
            case "FRIDAY":
                startCol = 5;
                break;
            case "SATURDAY":
                startCol = 6;
                break;
        }
        
        int totalDays = selectedMonth.lengthOfMonth();
        int date = 1;
        calendarPane.getChildren().clear();
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                DayInMonth temp = new DayInMonth("");
                temp.setToggleGroup(dayGroup);
                temp.setPrefSize(80, 80);
                temp.getStylesheets().add("desktopscheduler/view/desktopScheduler.css");
                temp.getStyleClass().add("dayInMonth");
                calendarPane.add(temp, col, row);
                if(row == 0 && col < startCol){
                    temp.setDisable(true);
                    temp.setDate(null);
                }
                else if(date > totalDays){
                    temp.setDisable(true);
                    temp.setDate(null);
                }
                else{
                    temp.setDate(selectedMonth.atDay(date));
                    temp.setText(Integer.toString(date));
                    date++;
                    if(temp.getDate().toString().equals(LocalDate.now().toString())){
                        dayGroup.selectToggle(temp);
                    }
                }
            }
        }
        
    }
    
    private void checkUpcomingAppts(){
        ArrayList<Appointment> apptsToday = DBDriver.getAppointmentList(LocalDate.now());
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.getDefault());
        for(Appointment a : apptsToday){
            LocalDateTime temp = LocalDateTime.parse(a.getStart(), dt);
            if(temp.isBefore(LocalDateTime.now().plusMinutes(16)) && temp.isAfter(LocalDateTime.now())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("You have an appointment within 15 minutes");
                alert.setContentText("");
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dayGroup = new ToggleGroup();
        selectedMonth = YearMonth.now();
        selectedDate = LocalDate.now();
        apptTable.setPlaceholder(new Label("No Apointments"));
        
        populateGrid();
        populateAppointments();
        
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        
        //Lambda expression used to make adding a listener to the button simpler
        backButton.setOnAction(event -> {
            selectedMonth = selectedMonth.minusMonths(1);
            selectedDate = null;
            dayGroup.selectToggle(null);
            populateGrid();
        });
        
        //Lambda expression used to make adding a listener to the button simpler
        forwardButton.setOnAction(event -> {
            selectedMonth = selectedMonth.plusMonths(1);
            selectedDate = null;
            dayGroup.selectToggle(null);
            populateGrid();
        });
        
        dayGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle){
                if(dayGroup.getSelectedToggle() != null){
                    DayInMonth temp;
                    temp = (DayInMonth)dayGroup.getSelectedToggle();
                    selectedDate = temp.getDate();
                    populateAppointments();
                }
            }
        });
        
        checkUpcomingAppts();
    }    
    
}
