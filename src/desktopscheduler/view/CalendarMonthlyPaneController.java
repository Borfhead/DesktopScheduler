/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import java.net.URL;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
    private ToggleGroup dayGroup;
    private LocalDate selectedDate;
    private YearMonth selectedMonth;
    
    
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
                }
                else if(date > totalDays){
                    temp.setDisable(true);
                }
                else{
                    temp.setDate(selectedMonth.atDay(date));
                    temp.setText(Integer.toString(date));
                    date++;
                }
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
        populateGrid();
        
        backButton.setOnAction(event -> {
            selectedMonth = selectedMonth.minusMonths(1);
            populateGrid();
        });
        
        forwardButton.setOnAction(event -> {
            selectedMonth = selectedMonth.plusMonths(1);
            populateGrid();
        });
    }    
    
}
