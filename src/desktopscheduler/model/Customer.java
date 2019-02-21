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
public class Customer {
    private int customerID;
    private String customerName;
    private int addressID;
    
    public Customer(){
        customerID = -1;
        customerName = "";
        addressID = -1;
    }
    
    public Customer(int customerID, String customerName, int addressID){
        this.addressID = addressID;
        this.customerID = customerID;
        this.customerName = customerName;
    }
    
    public void setCustomerID(int newID){
        customerID = newID;
    }
    
    public void setCustomerName(String newName){
        customerName = newName;
    }
    
    public void setAddressID(int newID){
        addressID = newID;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public int getAddressID(){
        return addressID;
    }
}
