package com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBarComponent;

import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class TopBackButton extends GraphicButton {
    
    public static int PREFERRED_WIDTH = 100;
    
    public TopBackButton(){
        this.setMinimumSize(new Dimension(PREFERRED_WIDTH, TopBar.TOP_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(PREFERRED_WIDTH, TopBar.TOP_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(PREFERRED_WIDTH, TopBar.TOP_BAR_HEIGHT));
        addMouseListener(this);
    }
    
    @Override
    public void paintContent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        String str = Localization.Get("#back");
        int shift = (int)(str.length() / 2.0 * 5);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 5 + y_shift);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.MAIN_MENU);
    }
    
}
