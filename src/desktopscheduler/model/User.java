/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.model;

/**
 *
 * @author Dylan
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    
    
    public User(){
        userID = -1;
        userName = "";
        password = "";
    }
    
    public User(int userID, String userName, String password){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }
    
    public void setUserID(int newID){
        userID = newID;
    }
    
    public void setUserName(String newUserName){
        userName = newUserName;
    }
    
    public void setPassword(String newPassword){
        password = newPassword;
    }
    
    public int getUserID(){
        return userID;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getPassword(){
        return password;
    }
}
