package com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents;

import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class HelpButton extends GraphicButton {

    public HelpButton(){
        this.setMinimumSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        this.setMaximumSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        this.setPreferredSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        addMouseListener(this);
    }
    
    @Override
    public void paintContent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.BOLD, 14));
        String str = Localization.Get("#help");
        int shift = (int)(str.length() / 2.0 * 8);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 6 + y_shift);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.HELP_MENU);
    }
    
}
