package com.latestagedesign.religiouswars.view.gui;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public abstract class GraphicRadioButton extends JComponent implements MouseListener, MouseMotionListener{
    
    protected int SIDE_GAP = 6;
    protected int OUTER_GAP = 3;
    
    private RadioButtonController controller;
    
    public int id;
    
    private boolean isPressedOnThis = false;
    
    public boolean isChosen = false;
    
    protected int x_shift = 0;
    protected int y_shift = 0;
    
    public GraphicRadioButton(int _id){this.id = _id;}
    
    public void InitController(RadioButtonController c){
        controller = c;
    }
    
    public abstract void PaintChosen(Graphics g);
    
    public abstract void PaintUnchosen(Graphics g);
    
    public abstract void OnSelected();

    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        x_shift = isPressedOnThis ? 1 : 0;
        y_shift = isPressedOnThis ? 1 : 0;
        
        if(isChosen)
            PaintChosen(g);
        else
            PaintUnchosen(g);
        
        g.setColor(Color.gray);
        g.drawRect(OUTER_GAP, OUTER_GAP, getWidth() - OUTER_GAP * 2, getHeight() - OUTER_GAP * 2);
        g.setColor(Color.black);
        g.drawRect(OUTER_GAP + x_shift, OUTER_GAP + y_shift, getWidth() - 1 - OUTER_GAP * 2 + x_shift, getHeight() - 1 - OUTER_GAP * 2 + y_shift);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.FireButtonChosen(this);
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
