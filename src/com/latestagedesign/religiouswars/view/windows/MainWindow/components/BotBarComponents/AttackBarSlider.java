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

import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public class AttackBarSlider extends JComponent implements MouseListener, MouseMotionListener{
    
    private boolean isMouseDown = false;
    
    private static int SIDE_GAPS = 70;
    
    private int maxNum = 1;
    
    public int curNum = 0;
    
    private int cur_x_pos = 0;
    
    private int left_x_pos = 0;
    private int right_x_pos = 0;
    
    public AttackBarSlider(){
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void InitMaxNum(int n){
        maxNum = n;
        curNum = maxNum/2;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(maxNum > 0){
            left_x_pos = SIDE_GAPS;
            right_x_pos = getWidth() - SIDE_GAPS;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.gray);
            g2.drawLine(left_x_pos, getHeight() / 2, right_x_pos, getHeight() / 2);

            cur_x_pos = (right_x_pos - left_x_pos) * curNum / maxNum + left_x_pos;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.black);
            g2.drawLine(cur_x_pos, getHeight() / 2 + 5, cur_x_pos, getHeight() / 2 - 5);

            g2.drawString("0", left_x_pos - 8, getHeight() / 2 - 4);
            g2.drawString(maxNum + "", right_x_pos + 3, getHeight() / 2 - 4);
            g2.drawString(curNum + "", getWidth()/2, getHeight() - 5);
        }
        else{
            g.setColor(Color.black);
            g2.drawString(Localization.Get("#no_such_unit_on_location"), getWidth()/2 - 45, getHeight()/2 + 5);
        }
    }
    
    private void RecountMousePosition(Point p){
        int newCur = (int)Math.round((p.x * 1.0 - left_x_pos * 1.0) * maxNum /(right_x_pos * 1.0 - left_x_pos * 1.0));
        if(newCur < 0) newCur = 0;
        if(newCur > maxNum) newCur = maxNum;
        if(newCur != curNum){
            curNum = newCur;
            repaint();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getPoint().x >= left_x_pos && e.getPoint().x <= right_x_pos && maxNum > 0)
            isMouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
        isMouseDown = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(isMouseDown){
            Point p = e.getPoint();
            RecountMousePosition(p);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
