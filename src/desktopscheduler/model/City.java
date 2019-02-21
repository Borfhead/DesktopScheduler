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
public class City {
    private int cityID;
    private String cityName;
    private int countryID;
    
    public City(){
        cityID = -1;
        cityName = "";
        countryID = -1;
    }
    
    public City(int cityID, String cityName, int countryID){
        this.cityID = cityID;
        this.cityName = cityName;
        this.countryID = countryID;
    }
    
    public void setCityID(int newID){
        cityID = newID;
    }
    
    public void setCityName(String newName){
        cityName = newName;
    }
    
    public void setCountryiD(int newID){
        countryID = newID;
    }
    
    public int getCityID(){
        return cityID;
    }
    
    public String getCityName(){
        return cityName;
    }
    
    public int getCountryID(){
        return countryID;
    }
}
