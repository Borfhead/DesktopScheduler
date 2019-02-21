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
public class Address {
    private int addressID;
    private String address;
    private String address2;
    private int cityID;
    private String postalCode;
    private String phone;
    
    public Address(){
        addressID = -1;
        address = "";
        address2 = "";
        cityID = -1;
        postalCode = "";
        phone = "";
    }
    
    public Address(int addressID, String address, String address2, 
            int cityID, String postalCode, String phone){
        this.addressID = addressID;
        this.address = address;
        this.address2 = address2;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    public void setAddressID(int newID){
        addressID = newID;
    }
    
    public void setAddress(String newAddress){
        address = newAddress;
    }
    
    public void setAddress2(String newAddress){
        address2 = newAddress;
    }
    
    public void setCityID(int newID){
        cityID = newID;
    }
    
    public void setPostalCode(String newPostalCode){
        postalCode = newPostalCode;
    }
    
    public void setPhone(String newPhone){
        phone = newPhone;
    }
    
    public int getAddressID(){
        return addressID;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getAddress2(){
        return address2;
    }
    
    public int getCityID(){
        return cityID;
    }
    
    public String getPostalCode(){
        return postalCode;
    }
    
    public String getPhone(){
        return phone;
    }
}
