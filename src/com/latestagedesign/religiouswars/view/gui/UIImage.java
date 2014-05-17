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
