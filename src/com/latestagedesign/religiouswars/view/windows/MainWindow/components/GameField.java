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
import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.sun.javafx.geom.Vec2f;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javafx.util.Pair;
import javax.swing.JComponent;

public class GameField extends JComponent implements ActionListener{
    
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
        if(fieldLocations == null || fieldLocations.size() <= 0){
            System.out.println("fieldLocations == null " + (fieldLocations == null));
            return;
        }
        g.setColor(Color.black);
        
        for(VOFieldLocation l : fieldLocations){
            System.out.println("l == null " + (l == null));
            System.out.println("l.data == null " + (l.data == null));
            System.out.println("l.data.borders == null " + (l.data.borders == null));
            for(Pair<Vec2f, Vec2f> line : l.data.borders){
                g.drawLine((int)(line.getKey().x * (getWidth() - 1)),
                        (int)(line.getKey().y * (getHeight() - 1)),
                        (int)(line.getValue().x * (getWidth() - 1)),
                        (int)(line.getValue().y * (getHeight() - 1)));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //switch(e.)
    }
}
