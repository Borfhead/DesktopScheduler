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
            System.out.println(e);
        }
        return false;
    }
    
    public static boolean insertAddress(String address, String address2, int cityId, String postal, String phone){
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS)){
            Statement stmt = conn.createStatement();
            String query = String.format("INSERT IGNORE INTO address(address, address2, cityId, postalCode, phone) "
                    + "VALUES("
                    + "'%s', '%s', '%x', '%s', '%s')", address, address2, cityId, postal, phone);
            stmt.executeUpdate(query);
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    
    public static boolean insertCustomer(String name, int addressId){
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS)){
            Statement stmt = conn.createStatement();
            String query = String.format("INSERT IGNORE INTO customer(customerName, addressId) VALUES('%s', '%x')", name, addressId);
            stmt.executeUpdate(query);
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    
    public static boolean insertAppointment(){
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("");
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }
}
