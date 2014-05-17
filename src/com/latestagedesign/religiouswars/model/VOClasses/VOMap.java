package com.latestagedesign.religiouswars.model.VOClasses;

import com.sun.javafx.geom.Vec2f;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class VOMap {
    public enum MapSize{
        GERMANY,
        UKRAINE,
        USA
    }
    
    public List<VOLocation> locations;
    
    public VOMap Clone(){
        VOMap clone = new VOMap();
        clone.locations = new ArrayList<VOLocation>();
        
        for(int i = 0; i < locations.size(); i++){
            clone.locations.add(locations.get(i).Clone(clone.locations));
        }
        
        return clone;
    }
    
    public VOLocation GetLocationById(Integer id){
        for(VOLocation loc : locations){
            if(loc.id == id) return loc;
        }
        
        return null;
    }
    
    public void AddLocation(int id, List<Integer> neighbours, int weight, ArrayList<Pair<Vec2f, Vec2f>> _borders, boolean isStart){
        if(locations == null) locations = new ArrayList<VOLocation>();
        VOLocation location = new VOLocation(id);
        location.neighbours = new ArrayList<VOLocation>();
        for (Integer neighbour : neighbours) {
            for (VOLocation loc : locations) {
                if (loc.id == neighbour) {
                    location.neighbours.add(loc);
                    if(!loc.neighbours.contains(location))
                        loc.neighbours.add(location);
                }
            }
        }
        
        location.CreateFieldLocation();
        location.borders = _borders;
        location.weight = weight;
        location.isStart = isStart;
        
        locations.add(location);
    }

    @Override
    public String toString() {
        String mapStr = "VOMap{locations={";
        for (VOLocation location : locations) {
            mapStr += location.toString() + ",";
        }
        
        mapStr = mapStr.substring(0, mapStr.length() - 1);
        
        mapStr += "}}";
        
        return mapStr; //To change body of generated methods, choose Tools | Templates.
    }
}
