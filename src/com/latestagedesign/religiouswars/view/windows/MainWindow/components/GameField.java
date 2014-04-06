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

package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.control.exceptions.InitializationException;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JComponent;

public class GameField extends JComponent {
    
    private FieldController controller;
    
    public List<VOFieldLocation> fieldLocations;
    
    public GameField() throws InitializationException{
        controller = FieldController.getinstance();
        controller.Init(this);
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        DrawField(g);
    }
    
    public void DrawField(Graphics g){
        g.setColor(Color.black);
        
        
        
        
        
    }
}
