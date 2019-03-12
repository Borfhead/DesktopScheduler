/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopscheduler.view;

import java.time.LocalDate;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Dylan
 */
public class DayInMonth extends ToggleButton{
    
    private LocalDate date;
    
    public DayInMonth(){
        super();
        date = LocalDate.now();
    }
    
    public DayInMonth(String text){
        super(text);
        date = LocalDate.now();
    }
    
    public void setDate(LocalDate toSet){
        date = toSet;
    }
    
    public LocalDate getDate(){
        return date;
    }
}
