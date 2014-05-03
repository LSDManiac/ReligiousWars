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
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class BuildBar extends JComponent{
    
    private BuildingUpgradeButton cityUpgrade;
    private BuildingUpgradeButton barrackUpgrade;
    private BuildingUpgradeButton churchUpgrade;
    
    public BuildBar(){
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        cityUpgrade = new BuildingUpgradeButton();
        barrackUpgrade = new BuildingUpgradeButton();
        churchUpgrade = new BuildingUpgradeButton();
        
        add(cityUpgrade);
        add(barrackUpgrade);
        add(churchUpgrade);
        
        this.setMinimumSize(new Dimension(Short.MIN_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE, BotBar.BOT_BAR_HEIGHT));
    }
    
    public void InitBuildButtons(){
        cityUpgrade.InitButton(VOFieldLocation.BuildingType.FARM, FieldController.getinstance().selectedBuildLocation);
        barrackUpgrade.InitButton(VOFieldLocation.BuildingType.BARRACK, FieldController.getinstance().selectedBuildLocation);
        churchUpgrade.InitButton(VOFieldLocation.BuildingType.TEMPLE, FieldController.getinstance().selectedBuildLocation);
        
        
    }
}
