package com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents;

import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class AttackBarSendButton extends GraphicButton{
    
    private static int PREFERRED_WIDTH = 120;
    
    public AttackBarSendButton(){
        this.setMinimumSize(new Dimension(PREFERRED_WIDTH, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(PREFERRED_WIDTH, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(PREFERRED_WIDTH, BotBar.BOT_BAR_HEIGHT));
        addMouseListener(this);
    }
    
    @Override
    public void paintContent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        String str = Localization.Get("#send");
        int shift = (int)(str.length() / 2.0 * 5);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 5 + y_shift);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        BotBar.getinstance().attackBar.FireAttack();
    }
    
}
