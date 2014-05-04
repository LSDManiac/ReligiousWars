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

import javax.swing.JComponent;

public class CustomBattleMenu extends JComponent {
    
    private static CustomBattleMenu _instance;
    public static CustomBattleMenu getinstance(){
        if(_instance == null) _instance = new CustomBattleMenu();
        return _instance;
    }
    
}
