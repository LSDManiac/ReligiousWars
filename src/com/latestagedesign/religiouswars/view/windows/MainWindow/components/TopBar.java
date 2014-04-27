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
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TopBar extends JComponent implements ActionListener{
    
    private static int TOP_BAR_HEIGHT = 30;
    
    public TopBar(){
        Button b = new Button("BACK");
        b.addActionListener(this);
        this.setLayout(new BorderLayout());
        add(b, BorderLayout.WEST);
        JPanel leftContainer = new JPanel();
        leftContainer.setLayout(new BoxLayout(leftContainer, BoxLayout.LINE_AXIS));
        leftContainer.add(new Button("plus"));
        leftContainer.add(new Button("sun"));
        leftContainer.add(new Button("moon"));
        add(leftContainer, BorderLayout.EAST);
        add(new Button("timer"), BorderLayout.CENTER);
        
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, TOP_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, TOP_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, TOP_BAR_HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FieldController.getinstance().CreateField();
    }
}
