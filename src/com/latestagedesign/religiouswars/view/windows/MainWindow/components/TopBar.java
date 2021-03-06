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

import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.control.field.FieldCreator;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBarComponent.TopInfoBar;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TopBar extends JComponent{
    
    private  static TopBar _instance;
    public static TopBar getinstance(){
        if(_instance == null) _instance = new TopBar();
        return _instance;
    }
    
    public static int TOP_BAR_HEIGHT = 40;
    
    private TopInfoBar infobar;
    
    public TopBar(){
        infobar = new TopInfoBar();
        
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        
        this.add(infobar);
        
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, TOP_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, TOP_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, TOP_BAR_HEIGHT));
    }
    
    public void RecountState(){
       infobar.UpdateInfo();
    }
}
