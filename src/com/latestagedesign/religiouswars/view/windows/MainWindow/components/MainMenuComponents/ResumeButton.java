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

package com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents;

import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class ResumeButton extends GraphicButton {

    public ResumeButton(){
        this.setMinimumSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        this.setMaximumSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        this.setPreferredSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        addMouseListener(this);
    }
    
    @Override
    public void paintContent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        Color backColor = Color.white;
        
        if(FieldController.getinstance().inField)
            backColor = Color.white;
        else
            backColor = new Color(200, 200, 200);
        
        g.setColor(backColor);
        g.fillRect(OUTER_GAP, OUTER_GAP, getWidth() - OUTER_GAP, getHeight() - OUTER_GAP);
        
        Color textColor = Color.black;
        if(FieldController.getinstance().inField)
            textColor = Color.black;
        else
            textColor = Color.gray;
        
        g.setColor(textColor);
        String str = Localization.Get("#resume");
        int shift = (int)(str.length() / 2.0 * 5);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 5 + y_shift);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(FieldController.getinstance().inField)
            MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.GAME);
    }
    
}