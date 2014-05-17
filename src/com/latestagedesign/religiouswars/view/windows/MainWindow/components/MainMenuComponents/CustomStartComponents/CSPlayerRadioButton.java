package com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartComponents;

import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.gui.GraphicRadioButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.CustomBattleMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class CSPlayerRadioButton extends GraphicRadioButton {
    
    public static int BUTTON_HEIGHT = 30;
    public static int BUTTON_WIDTH = 40;
    
    public int playerNum = 1;
    
    public CSPlayerRadioButton(int _id) {
        super(_id);
        this.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        addMouseListener(this);
    }

    @Override
    public void PaintChosen(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(OUTER_GAP + x_shift, OUTER_GAP + y_shift, getWidth() - 2 - OUTER_GAP + x_shift, getHeight() - 2 - OUTER_GAP + y_shift);
        
        g.setColor(Color.black);
        String str = playerNum + "";
        int shift = (int)(str.length() / 2.0 * 5);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 5 + y_shift);
    }

    @Override
    public void PaintUnchosen(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(OUTER_GAP + x_shift, OUTER_GAP + y_shift, getWidth() - 2 - OUTER_GAP + x_shift, getHeight() - 2 - OUTER_GAP + y_shift);
        
        g.setColor(Color.black);
        String str = playerNum + "";
        int shift = (int)(str.length() / 2.0 * 5);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 5 + y_shift);
    }

    @Override
    public void OnSelected() {
        CustomBattleMenu.getinstance().ParametersChanged();
    }

}
