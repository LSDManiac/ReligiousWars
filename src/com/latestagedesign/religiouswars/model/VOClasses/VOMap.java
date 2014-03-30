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

package com.latestagedesign.religiouswars.model.VOClasses;

import java.util.ArrayList;
import java.util.List;

public class VOMap {
    public enum MapSize{
        GERMANY,
        UKRAINE,
        USA
    }
    
    public List<VOLocation> locations;
    
    public VOMap Clone(){
        return new VOMap();
    }
    
    public void AddLocation(int id, List<Integer> neighbours){
        if(locations == null) locations = new ArrayList<VOLocation>();
        VOLocation location = new VOLocation(id);
        location.neighbours = new ArrayList<VOLocation>();
        for (Integer neighbour : neighbours) {
            for (VOLocation loc : locations) {
                if (loc.id == neighbour) {
                    location.neighbours.add(loc);
                }
            }
        }
        locations.add(location);
    }
}
