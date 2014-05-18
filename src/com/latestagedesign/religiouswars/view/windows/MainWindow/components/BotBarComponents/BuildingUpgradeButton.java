package com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.control.field.UnitController;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public class BuildingUpgradeButton extends GraphicButton implements MouseListener, MouseMotionListener{
    
    private VOFieldLocation.BuildingType type;
    private int LocationId;
    
    public BuildingUpgradeButton(){
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        addMouseListener(this);
    }
    
    public void InitButton(VOFieldLocation.BuildingType _type, int locationId){
        type = _type;
        LocationId = locationId;
        repaint();
    }
    
    private void DrawSymbol(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(type == VOFieldLocation.BuildingType.FARM){
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.black);
            g2.drawOval(1 + SIDE_GAP + x_shift, 1 + SIDE_GAP + y_shift, getHeight() - 2 - SIDE_GAP * 2 + x_shift, getHeight() - 2 - SIDE_GAP * 2 + y_shift);
        }
        else if(type == VOFieldLocation.BuildingType.BARRACK){
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.black);
            g2.drawRect(1 + SIDE_GAP + x_shift, 1 + SIDE_GAP + y_shift, getHeight() - 2 - SIDE_GAP * 2 + x_shift, getHeight() - 2 - SIDE_GAP * 2 + y_shift);
        }
        else if(type == VOFieldLocation.BuildingType.TEMPLE){
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.black);
            g2.drawRoundRect(1 + SIDE_GAP + x_shift, 1 + SIDE_GAP + y_shift, getHeight() - 2 - SIDE_GAP * 2 + x_shift, getHeight() - 2 - SIDE_GAP * 2 + y_shift, getHeight()/3, getHeight()/3);
        }
        
        g2.setStroke(new BasicStroke(1));
    }

    @Override
    public void paintContent(Graphics g) {
        g.setFont(new Font("Courier", Font.BOLD, 14));
        DrawSymbol(g);
        int count = 0;
        if(FieldController.getinstance().curMap.GetLocationById(LocationId).fLoc.buildingsOnLocation.containsKey(type)){
            count = FieldController.getinstance().curMap.GetLocationById(LocationId).fLoc.buildingsOnLocation.get(type);
        }
        
        g.drawString(count + "", (int)(- SIDE_GAP / 2 + getHeight() / 2 - 0.5f) + x_shift, getHeight()/2 + 5 + y_shift);
        
        int curUnitNum = 0;
        
        if(FieldController.getinstance().curMap.GetLocationById(LocationId).fLoc.unitsOnLocation.containsKey(PlayersController.getinstance().GetCurrentPlayerId())){
            if(FieldController.getinstance().curMap.GetLocationById(LocationId).fLoc.unitsOnLocation.get(PlayersController.getinstance().GetCurrentPlayerId()).containsKey(UnitController.GetUnitTypeByBuildingType(type))){
                curUnitNum = FieldController.getinstance().curMap.GetLocationById(LocationId).fLoc.unitsOnLocation.get(PlayersController.getinstance().GetCurrentPlayerId()).get(UnitController.GetUnitTypeByBuildingType(type));
            }
        }
        
        int plusNum = count * FieldController.getinstance().curMap.GetLocationById(LocationId).fLoc.getMultiplierByBuildingType(type);
        
        g.drawString(Localization.Get("#building_" + type), getHeight() + 15 + x_shift, getHeight()/2 - 5 + y_shift);
        g.drawString(curUnitNum + " (+" + plusNum + ")", getHeight() + 15 + x_shift, getHeight()/2 + 13 + y_shift);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        FieldController.getinstance().FireBuild(type);
    }

}
