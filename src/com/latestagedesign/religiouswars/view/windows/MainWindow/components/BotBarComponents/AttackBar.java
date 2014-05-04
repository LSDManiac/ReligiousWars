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

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.control.field.UnitController;
import com.latestagedesign.religiouswars.view.gui.RadioButtonController;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class AttackBar extends JComponent{
    
    private int attackFromLoc;
    
    private AttackBarRadioButton priestButton;
    private AttackBarRadioButton soldierButton;
    
    private RadioButtonController radioController;
    
    private AttackBarSlider slider;
    
    private AttackBarSendButton sendButton;
    
    public AttackBar(){
        priestButton = new AttackBarRadioButton(0);
        soldierButton = new AttackBarRadioButton(1);
        
        priestButton.sendType = UnitController.UnitType.PRIEST;
        soldierButton.sendType = UnitController.UnitType.SOLDIER;
        
        radioController = new RadioButtonController();
        
        radioController.AddRadioButton(soldierButton);
        radioController.AddRadioButton(priestButton);
        
        slider = new AttackBarSlider();
        
        sendButton = new AttackBarSendButton();
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        add(priestButton);
        add(soldierButton);
        add(slider);
        add(sendButton);
        
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
    }
    
    public void FireTypeChanged(){
        if(GetCurrentType() == UnitController.UnitType.SOLDIER){
            slider.InitMaxNum(FieldController.getinstance().curMap.GetLocationById(FieldController.getinstance().selectedAttackingLocation).fLoc.unitsOnLocation.get(PlayersController.getinstance().GetCurrentPlayerId()).get(UnitController.UnitType.SOLDIER));
        }
        else if(GetCurrentType() == UnitController.UnitType.PRIEST){
            slider.InitMaxNum(FieldController.getinstance().curMap.GetLocationById(FieldController.getinstance().selectedAttackingLocation).fLoc.unitsOnLocation.get(PlayersController.getinstance().GetCurrentPlayerId()).get(UnitController.UnitType.PRIEST));
        }
    }
    
    public void FireAttack(){
        FieldController.getinstance().FireAttack(slider.curNum, GetCurrentType());
    }
    
    private UnitController.UnitType GetCurrentType(){
        int selected = radioController.GetChosenId();
        if(selected == 0) return UnitController.UnitType.PRIEST;
        if(selected == 1) return UnitController.UnitType.SOLDIER;
        
        return UnitController.UnitType.SOLDIER;
    }
}
