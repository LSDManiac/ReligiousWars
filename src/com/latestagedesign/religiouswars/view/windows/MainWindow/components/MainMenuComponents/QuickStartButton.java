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

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class QuickStartButton extends GraphicButton{
    
    private static int QUICK_START_PLAYER_NUM = 3;
    private static VOMap.MapSize QUICK_START_MAP = VOMap.MapSize.USA;
    
    public QuickStartButton(){
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
        String str = Localization.Get("#quick_start");
        int shift = (int)(str.length() / 2.0 * 5);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 5 + y_shift);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PlayersController.getinstance().CreatePlayers(QUICK_START_PLAYER_NUM);
        FieldController.getinstance().CreateField(QUICK_START_PLAYER_NUM, QUICK_START_MAP);
        MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.GAME);
    }
    
}
