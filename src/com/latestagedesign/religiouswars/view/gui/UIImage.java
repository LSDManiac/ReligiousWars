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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;
import org.openide.util.ImageUtilities;

public class UIImage extends JComponent{
    Image image = null;
    public UIImage(String imageName){
        image = ImageUtilities.loadImage(imageName);
        setBackground(Color.red);
        
    }
    
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 100, 100, this);
    }
    
}
