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

package com.latestagedesign.religiouswars.control.field;

import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import java.util.HashMap;
import java.util.Map;

public class FieldCreator {
    
    private static Map<VOMap.MapSize, VOMap> MapGraphs;
    
    public static VOMap CreateMap(int playerNum, VOMap.MapSize size){
        VOMap map = CreateMapGraph(size);
        
        // TODO: Set Players Start Locations
        
        // TODO: Set start locations units
        
        return map;
    }
    
    private static VOMap CreateMapGraph(VOMap.MapSize size){
        if(MapGraphs == null) MapGraphs = new HashMap<VOMap.MapSize, VOMap>();
        if(!MapGraphs.containsKey(size)){
            VOMap map = new VOMap();
            MapGraphs.put(size, map);
        }
        
        return MapGraphs.get(size).Clone();
    }
}
