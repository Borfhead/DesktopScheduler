/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.model;
import java.sql.*;
/**
 *
 * @author Dylan
 */
public class DBDriver {
    private static final String URL = "jdbc:mysql://52.206.157.109/U05AXu";
    private static final String USER = "U05AXu";
    private static final String PASS = "53688446515";
//        try(Connection conn = DriverManager.getConnection(url, user, pass)){
//            System.out.println(conn);
//        }
//        catch(SQLException e){
//            System.out.println("Didn't work");
//        }
    
    public static boolean authenticate(String userName, String password){
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT password FROM user WHERE userName = '" +userName+ "'");
            rs.first();
            if(rs.getString(1).equals(password)){
                return true;
            }
        }
        catch(SQLException e){
            
        }
        return false;
    }
}
