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

package com.latestagedesign.religiouswars.model;

import java.awt.Color;

public class Constants {
    public static Color BORDER_UNSELECTED_COLOR = Color.black;
    public static Color BORDER_ATTACKED_COLOR = Color.red;
    public static Color BORDER_ATTACKING_COLOR = Color.green;
    public static Color BORDER_BUILDING_COLOR = Color.blue;
    
    public static int BORDER_UNSELECTED_SIZE = 1;
    public static int BORDER_ATTACKED_SIZE = 5;
    public static int BORDER_ATTACKING_SIZE = 5;
    public static int BORDER_BUILDING_SIZE = 5;
    
    public static int FARM_MULTIPLICATOR = 1;
    public static int BARRACK_MULTIPLICATOR = 1;
    public static int TEMPLE_MULTIPLICATOR = 1;
    
    public static Color NOBODY_PROVINCE_COLOR = new Color(215, 215, 215);
    
    public static int START_PRIEST_NUMBER = 10;
    public static int START_SOLDIER_NUMBER = 10;
    public static int START_PEASANT_NUMBER = 100;
    
    public static int START_EMPTY_PEASANT_NUMBER = 50;
}
