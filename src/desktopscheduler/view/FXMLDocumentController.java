/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Dylan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {

//        String url = "jdbc:mysql://52.206.157.109/U05AXu";
//        String user = "U05AXu";
//        String pass = "53688446515";
//        try(Connection conn = DriverManager.getConnection(url, user, pass)){
//            System.out.println(conn);
//        }
//        catch(SQLException e){
//            System.out.println("Didn't work");
//        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
