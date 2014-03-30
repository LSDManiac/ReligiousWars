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

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class BotBar extends JComponent {
    
    public enum BotBarStatus{
        NONE,
        HIDDEN,
        BUILDING,
        ATTACK
    }
    
    private BotBarStatus curStatus = BotBarStatus.HIDDEN;
    
    private JPanel buildBar;
    private JPanel hiddenBar;
    private JPanel attackBar;
    
    public BotBar(){
        
        buildBar = new JPanel();
        hiddenBar = new JPanel();
        attackBar = new JPanel();
        setLayout(new BorderLayout());
        
    }
    
    public void SwitchToState(BotBarStatus newStatus){
        curStatus = newStatus;
        switch(newStatus){
            case ATTACK:{
                setLayout(new BorderLayout());
                add(attackBar, BorderLayout.CENTER);
            } break;
            case BUILDING:{
                setLayout(new BorderLayout());
                add(buildBar, BorderLayout.CENTER);
            } break;
            case HIDDEN:{
                setLayout(new BorderLayout());
                add(hiddenBar, BorderLayout.CENTER);
            } break;
            
        }
        
    }
}
