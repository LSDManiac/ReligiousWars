package com.latestagedesign.religiouswars.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GraphicLabel extends JComponent{
    
    String string = "";
    
    public GraphicLabel(String str){
        string = str;
        
        this.setMinimumSize(new Dimension((int)(string.length() * 7), 14));
        this.setMaximumSize(new Dimension((int)(string.length() * 7), 14));
        this.setPreferredSize(new Dimension((int)(string.length() * 7), 14));
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        int shift = (int)(string.length() / 2.0 * 5.5);
        g.drawString(string, getWidth()/2 - shift, getHeight()/2 + 5);
    }
}
