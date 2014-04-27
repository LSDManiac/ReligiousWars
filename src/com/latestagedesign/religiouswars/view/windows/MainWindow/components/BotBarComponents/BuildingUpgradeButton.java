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

package com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents;

import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public class BuildingUpgradeButton extends JComponent implements MouseListener, MouseMotionListener{
    
    private VOFieldLocation.BuildingType type;
    
    public BuildingUpgradeButton(){
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
    }
    
    public void InitButton(VOFieldLocation.BuildingType _type, int currentNum){
        type = _type;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(0, 0, getWidth() - 1, getHeight() - 1);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        FieldController.getinstance().FireBuild(type);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
