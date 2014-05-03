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

import com.sun.javafx.geom.Vec2f;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class VOLocation {
    
    public int id;
    public List<VOLocation> neighbours;
    public int weight;
    public boolean isStart;
    
    public ArrayList<Pair<Vec2f, Vec2f>> borders;
    
    public VOFieldLocation fLoc;
    
    public VOLocation Clone(List<VOLocation> locations){
        VOLocation clone = new VOLocation(id);
        clone.weight = weight;
        clone.isStart = isStart;
        clone.neighbours = new ArrayList<VOLocation>();
        
        for (VOLocation location : locations) {
            for (VOLocation neighbour : neighbours) {
                if (location.id == neighbour.id) {
                    clone.neighbours.add(location);
                    if (!location.neighbours.contains(clone)) {
                        location.neighbours.add(clone);
                    }
                }
            }
        }
        
        clone.borders = new ArrayList<Pair<Vec2f, Vec2f>>(borders);
        clone.CreateFieldLocation();
        
        return clone;
    }
    
    public VOLocation(){}
    
    public VOLocation(int id){
        this.id = id;
    }
    
    public VOLocation GetNeighbourById(Integer id){
        for(VOLocation loc : neighbours){
            if(loc.id == id) return loc;
        }
        
        return null;
    }
    
    public void CreateFieldLocation(){
        fLoc = new VOFieldLocation();
        fLoc.data = this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof VOLocation)) return false;
        VOLocation loc = (VOLocation)obj;
        return loc.id == this.id;
    }

    @Override
    public String toString() {
        String locStr = "VOLocation{id=" + id + ",weight=" + weight + "neighbours={";
        
        for(VOLocation neighbour : neighbours){
            locStr += neighbour.id + ",";
        }
        
        if(!neighbours.isEmpty())
            locStr = locStr.substring(0, locStr.length() - 1);
        
        locStr += "}}";
        
        return locStr; //To change body of generated methods, choose Tools | Templates.
    }
    
}
