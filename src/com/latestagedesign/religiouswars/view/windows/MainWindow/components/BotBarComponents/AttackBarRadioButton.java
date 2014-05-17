package com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents;

import com.latestagedesign.religiouswars.control.field.UnitController;
import com.latestagedesign.religiouswars.view.gui.GraphicRadioButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AttackBarRadioButton extends GraphicRadioButton {
    
    public UnitController.UnitType sendType;
    
    public AttackBarRadioButton(int _id) {
        super(_id);
        this.setMinimumSize(new Dimension(BotBar.BOT_BAR_HEIGHT, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(BotBar.BOT_BAR_HEIGHT, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(BotBar.BOT_BAR_HEIGHT, BotBar.BOT_BAR_HEIGHT));
        addMouseListener(this);
    }
    
    private void DrawSymbol(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(sendType == UnitController.UnitType.PRIEST){
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.black);
            g2.drawLine((int)(getWidth() / 2 + x_shift), (int)(1 + SIDE_GAP + y_shift),
                    (int)(getWidth() - 2 - SIDE_GAP + x_shift), (int)(getHeight() / 2 + y_shift));
            g2.drawLine((int)(getWidth() / 2 + x_shift), (int)(1 + SIDE_GAP + y_shift),
                    (int)(1 + SIDE_GAP + x_shift), (int)(getHeight() / 2 + y_shift));
            g2.drawLine((int)(getWidth() / 2 + x_shift), (int)(getHeight() - 2 - SIDE_GAP + y_shift),
                    (int)(getWidth() - 2 - SIDE_GAP + x_shift), (int)(getHeight() / 2 + y_shift));
            g2.drawLine((int)(getWidth() / 2 + x_shift), (int)(getHeight() - 2 - SIDE_GAP + y_shift),
                    (int)(1 + SIDE_GAP + x_shift), (int)(getHeight() / 2 + y_shift));
        }
        else if(sendType == UnitController.UnitType.SOLDIER){
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.black);
            
            g2.drawLine((int)(getWidth() / 2 + x_shift), (int)(1 + SIDE_GAP + y_shift),
                    (int)(getWidth() / 2 + x_shift), (int)(getHeight() - 2 - SIDE_GAP + y_shift));
            g2.drawLine(getWidth() - 2 - SIDE_GAP + x_shift, (int)(getHeight() / 2 + y_shift),
                    (int)(1 + SIDE_GAP + x_shift), (int)(getHeight() / 2 + y_shift));
            
            //1 + SIDE_GAP + x_shift | 1 + SIDE_GAP + y_shift | getWidth() - 2 - SIDE_GAP * 2 + x_shift | getHeight() - 2 - SIDE_GAP * 2 + y_shift
            // getWidth() / 2 - SIDE_GAP / 2 + x_shift - 0.5
            //  - SIDE_GAP / 2 + getHeight() / 2 + y_shift - 0.5
        }
        
        g2.setStroke(new BasicStroke(1));
    }
    
    @Override
    public void PaintChosen(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(OUTER_GAP + x_shift, OUTER_GAP + y_shift, getWidth() - 2 - OUTER_GAP + x_shift, getHeight() - 2 - OUTER_GAP + y_shift);
        DrawSymbol(g);
    }

    @Override
    public void PaintUnchosen(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(OUTER_GAP + x_shift, OUTER_GAP + y_shift, getWidth() - 2 - OUTER_GAP + x_shift, getHeight() - 2 - OUTER_GAP + y_shift);
        DrawSymbol(g);
    }

    @Override
    public void OnSelected() {
        BotBar.getinstance().attackBar.FireTypeChanged();
    }
}
