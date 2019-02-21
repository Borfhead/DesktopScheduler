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
public class Country {
    private int countryID;
    private String countryName;
    
    
    public Country(){
        countryID = -1;
        countryName = "";
    }
    
    public Country(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }
    
    public void setCountryID(int newID){
        countryID = newID;
    }
    
    public void setCountryName(String newName){
        countryName = newName;
    }
}
