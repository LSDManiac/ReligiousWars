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
