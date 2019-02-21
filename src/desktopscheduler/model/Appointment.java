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
public class Appointment {
    private int apptID;
    private int customerID;
    private int userID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private String start;
    private String end;
    
    
    public Appointment(){
        apptID = -1;
        customerID = -1;
        userID = -1;
        title = "";
        description = "";
        location = "";
        contact = "";
        type = "";
        url = "";
        start = "";
        end = "";
    }
    
    public Appointment(int apptID, int customerID, int userID, String title,
            String description, String location, String contact, String type,
            String url, String start, String end){
        this.apptID = apptID;
        this.customerID = customerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
    }
    
    public void setApptID(int newID){
        apptID = newID;
    }
    
    public void setCustomerID(int newID){
        customerID = newID;
    }
    
    public void setUserID(int newID){
        userID = newID;
    }
    
    public void setTitle(String newTitle){
        title = newTitle;
    }
    
    public void setDescription(String newDescription){
        description = newDescription;
    }
    
    public void setLocation(String newLocation){
        location = newLocation;
    }
    
    public void setContact(String newContact){
        contact = newContact;
    }
    
    public void setType(String newType){
        type = newType;
    }
    
    public void setUrl(String newUrl){
        url = newUrl;
    }
    
    public void setStart(String newStart){
        start = newStart;
    }
    
    public void setEnd(String newEnd){
        end = newEnd;
    }
    
    public int getApptID(){
        return apptID;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public int getUserID(){
        return userID;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getLocation(){
        return location;
    }
    
    public String getContact(){
        return contact;
    }
    
    public String getType(){
        return type;
    }
    
    public String getUrl(){
        return url;
    }
    
    public String getStart(){
        return start;
    }
    
    public String getEnd(){
        return end;
    }
    
}
