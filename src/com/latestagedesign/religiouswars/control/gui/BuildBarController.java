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

package com.latestagedesign.religiouswars.control.gui;

import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBarComponents.BuildBar;

public class BuildBarController {
    public enum UpgradeType{
        NONE,
        CITY,
        BARRACK,
        CHURCH
    }
    private BuildBar vievElement;
    
    public void Init(BuildBar bar){
        vievElement = bar;
    }
    
    public void ShowBuildBar(VOLocation loc){
        
        
    }
    
}