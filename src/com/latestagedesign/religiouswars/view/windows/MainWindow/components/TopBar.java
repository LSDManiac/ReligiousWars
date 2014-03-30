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

import java.awt.BorderLayout;
import java.awt.Button;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TopBar extends JComponent {
    
    public TopBar(){
        this.setLayout(new BorderLayout());
        add(new Button("back"), BorderLayout.WEST);
        JPanel leftContainer = new JPanel();
        leftContainer.setLayout(new BoxLayout(leftContainer, BoxLayout.LINE_AXIS));
        leftContainer.add(new Button("plus"));
        leftContainer.add(new Button("sun"));
        leftContainer.add(new Button("moon"));
        add(leftContainer, BorderLayout.EAST);
        add(new Button("timer"), BorderLayout.CENTER);
        
        
        
        
        
    }
    
}
