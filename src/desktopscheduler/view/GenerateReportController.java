/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import desktopscheduler.model.DBDriver;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dylan
 */
public class GenerateReportController implements Initializable {

    @FXML TableView reportTable;
    @FXML ComboBox reportComboBox;
    
    @FXML
    private void generateButtonPressed() throws SQLException{
        reportTable.getColumns().clear();
        switch(reportComboBox.getSelectionModel().getSelectedIndex()){
            case 0:
                TableColumn month = new TableColumn("Month");
                month.setCellValueFactory(new PropertyValueFactory<>("month"));
                TableColumn types = new TableColumn("Types");
                types.setCellValueFactory(new PropertyValueFactory<>("types"));
                reportTable.getColumns().addAll(month, types);
                reportTable.setItems(FXCollections.observableArrayList(DBDriver.getAppointmentTypes()));
                break;
            case 1:
                TableColumn id = new TableColumn("User ID");
                id.setCellValueFactory(new PropertyValueFactory<>("userID"));
                TableColumn title = new TableColumn("Title");
                title.setCellValueFactory(new PropertyValueFactory<>("title"));
                TableColumn description = new TableColumn("Description");
                description.setCellValueFactory(new PropertyValueFactory<>("description"));
                TableColumn start = new TableColumn("Start");
                start.setCellValueFactory(new PropertyValueFactory<>("start"));
                TableColumn end = new TableColumn("End");
                end.setCellValueFactory(new PropertyValueFactory<>("end"));
                reportTable.getColumns().addAll(id, title, description, start, end);
                reportTable.setItems(FXCollections.observableArrayList(DBDriver.getConsultantSchedule()));
                break;
            case 2:
                TableColumn city = new TableColumn("City");
                city.setCellValueFactory(new PropertyValueFactory<>("city"));
                TableColumn appts = new TableColumn("Appointments");
                appts.setCellValueFactory(new PropertyValueFactory<>("appts"));
                reportTable.getColumns().addAll(city, appts);
                reportTable.setItems(FXCollections.observableArrayList(DBDriver.getCityReport()));
                break;
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reportComboBox.setItems(FXCollections.observableArrayList("Appointment Types", "User Schedule", "Appointments/City"));
        reportComboBox.getSelectionModel().select(1);
    }    
    
}
