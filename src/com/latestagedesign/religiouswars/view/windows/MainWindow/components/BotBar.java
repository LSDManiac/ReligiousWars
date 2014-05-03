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

package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents.AttackBar;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents.BuildBar;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class BotBar extends JComponent {
    
    private  static BotBar _instance;
    public static BotBar getinstance(){
        if(_instance == null) _instance = new BotBar();
        return _instance;
    }
    
    public enum BotBarStatus{
        NONE,
        HIDDEN,
        BUILDING,
        ATTACK
    }
    
    public static int BOT_BAR_HEIGHT = 50;
    
    private BotBarStatus curStatus = BotBarStatus.HIDDEN;
    
    public BuildBar buildBar;
    public JPanel hiddenBar;
    public AttackBar attackBar;
    
    public BotBar(){
        
        buildBar = new BuildBar();
        hiddenBar = new JPanel();
        attackBar = new AttackBar();
        hiddenBar.setLayout(new BorderLayout());
        hiddenBar.add(new Button("hidden"), BorderLayout.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(buildBar);
        add(attackBar);
        add(hiddenBar);
        SwitchToState(BotBarStatus.HIDDEN);
        
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, BOT_BAR_HEIGHT));
    }
    
    public void RecountState(){
        if(FieldController.getinstance().curState == FieldController.ControllerStates.NOTHING) SwitchToState(BotBarStatus.HIDDEN);
        if(FieldController.getinstance().curState == FieldController.ControllerStates.ATTACKING) SwitchToState(BotBarStatus.ATTACK);
        if(FieldController.getinstance().curState == FieldController.ControllerStates.BUILDING) SwitchToState(BotBarStatus.BUILDING);
    }
    
    
    
    public void SwitchToState(BotBarStatus newStatus){
        curStatus = newStatus;
        
        attackBar.setVisible(false);
        buildBar.setVisible(false);
        hiddenBar.setVisible(false);
        
        switch(newStatus){
            case ATTACK:{
                attackBar.setVisible(true);
                attackBar.FireTypeChanged();
            } break;
            case BUILDING:{
                buildBar.setVisible(true);
                buildBar.InitBuildButtons();
            } break;
            case HIDDEN:{
                hiddenBar.setVisible(true);
            } break;
        }
        this.updateUI();
        this.setBounds(this.getBounds());
    }
}
