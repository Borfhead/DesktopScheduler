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
public class CityReport {
    private String city;
    private String appts;
    
    public CityReport(String city, String appts){
        this.city = city;
        this.appts = appts;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getAppts(){
        return appts;
    }
}
