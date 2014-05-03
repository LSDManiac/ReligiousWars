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

package com.latestagedesign.religiouswars.view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public abstract class GraphicButton extends JComponent implements MouseListener, MouseMotionListener{
    
    protected int SIDE_GAP = 5;
    protected int OUTER_GAP = 2;
    
    private boolean isPressedOnThis = false;
    
    protected int x_shift = 0;
    protected int y_shift = 0;
    
    public abstract void paintContent(Graphics g);
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        x_shift = isPressedOnThis ? 1 : 0;
        y_shift = isPressedOnThis ? 1 : 0;
        
        paintContent(g);
        
        g.setColor(Color.gray);
        g.drawRect(OUTER_GAP, OUTER_GAP, getWidth() - OUTER_GAP * 2, getHeight() - OUTER_GAP * 2);
        g.setColor(Color.black);
        g.drawRect(OUTER_GAP + x_shift, OUTER_GAP + y_shift, getWidth() - 1 - OUTER_GAP * 2 + x_shift, getHeight() - 1 - OUTER_GAP * 2 + y_shift);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        isPressedOnThis = true;
        repaint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        isPressedOnThis = false;
        repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        isPressedOnThis = false;
        repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
