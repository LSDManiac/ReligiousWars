package com.latestagedesign.religiouswars.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GraphicLabel extends JComponent{
    
    String string = "";
    
    public GraphicLabel(String str){
        string = str;
        
        this.setMinimumSize(new Dimension((int)(string.length() * 10), 20));
        this.setMaximumSize(new Dimension((int)(string.length() * 10), 20));
        this.setPreferredSize(new Dimension((int)(string.length() * 10), 20));
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.BOLD, 14));
        int shift = (int)(string.length() / 2.0 * 8);
        g.drawString(string, getWidth()/2 - shift, getHeight()/2 + 6);
    }
}
