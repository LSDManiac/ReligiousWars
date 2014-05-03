/*
 * Late Stage Design
 * Created by Gregory
 * -------------------------------------------
 * Engoy the Dude's Favorite Coctail
 * 
 * Ingredients:
 * - 2 oz Vodka
 * - 1 oz Kahlúa
 * - Heavy cream
 * - Old Fashioned glass
 * 
 * How To Make:
 * Add the vodka and Kahlúa to an Old Fashioned glass filled with ice.
 * Top with a large splash of heavy cream and stir.
 * 
 * Have a nise day!
 */

package com.latestagedesign.religiouswars.view.gui;

import java.util.ArrayList;

public class RadioButtonController {
    
    private ArrayList<GraphicRadioButton> buttons;
    
    public RadioButtonController(){
        buttons = new ArrayList<GraphicRadioButton>();
    }
    
    public void AddRadioButton(GraphicRadioButton b){
        if(buttons.isEmpty())b.isChosen = true;
        else b.isChosen = false;
        
        if(!buttons.contains(b)){
            buttons.add(b);
            b.InitController(this);
        }
        
        b.repaint();
    }
    
    public void FireButtonChosen(GraphicRadioButton b){
        for(GraphicRadioButton lb : buttons){
            lb.isChosen = false;
            lb.repaint();
        }
        
        b.isChosen = true;
        b.OnSelected();
        b.repaint();
    }
    
    public int GetChosenId(){
        for(GraphicRadioButton lb : buttons){
            if(lb.isChosen)
                return lb.id;
        }
        
        return -1;
    }
}
