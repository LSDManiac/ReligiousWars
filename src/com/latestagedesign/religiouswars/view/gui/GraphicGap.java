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

import java.awt.Dimension;
import javax.swing.JComponent;

public class GraphicGap extends JComponent{
    public GraphicGap(){
       this.setMinimumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
       this.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
       this.setPreferredSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
    }
}
