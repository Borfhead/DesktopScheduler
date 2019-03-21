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
public class TypeReport {
    private String month;
    private String types;
    
    public TypeReport(String m, String t){
        month = m;
        types = t;
    }
    
    public String getMonth(){
        return month;
    }
    
    public String getTypes(){
        return types;
    }
}
