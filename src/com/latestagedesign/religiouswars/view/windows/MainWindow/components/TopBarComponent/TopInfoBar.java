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

package com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBarComponent;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class TopInfoBar extends JComponent {
    
    public TopInfoBar(){
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, TopBar.TOP_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, TopBar.TOP_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, TopBar.TOP_BAR_HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        String text = Localization.Get("#top_info_bar_text").replaceAll("%name%", PlayersController.getinstance().GetCurrentPlayerName());
        g.setColor(Color.black);
        g.drawString(text, 20, getHeight()/2 + 5);
        
        g.setColor(PlayersController.getinstance().GetCurrentPlayerColor());
        g.fillRect(getWidth() - 69, 8, 61, getHeight() - 16);
        
        g.setColor(Color.black);
        g.drawRect(getWidth() - 70, 7, 62, getHeight() - 15);
    }
    
    public void UpdateInfo(){
        repaint();
    }
}
