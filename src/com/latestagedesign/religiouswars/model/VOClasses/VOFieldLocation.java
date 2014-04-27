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

import com.latestagedesign.religiouswars.control.field.UnitController;
import com.latestagedesign.religiouswars.model.Constants;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class VOFieldLocation {
    public enum BuildingType{
        FARM,
        BARRACK,
        TEMPLE
    }
    
    private BuildingType buildingOrder[] = {BuildingType.FARM,
                                            BuildingType.BARRACK,
                                            BuildingType.TEMPLE};
    
    public VOLocation data;
    
    public Polygon polygon;
    
    public Color curColor = Color.gray;
    
    public String curPlayer;
    
    public VOFieldLocation(){
        unitsOnLocation = new HashMap<String, HashMap<UnitController.UnitType, Integer>>();
        buildingsOnLocation = new HashMap<BuildingType, Integer>();
    }
    
    public HashMap<String, HashMap<UnitController.UnitType, Integer>> unitsOnLocation;
    
    public HashMap<BuildingType, Integer> buildingsOnLocation;
    
    public void OnTurn(){
        
        
        for (BuildingType buildingOrder : buildingOrder) {
            if (buildingsOnLocation.containsKey(buildingOrder)) {
                ProcessBuildingAction(buildingOrder, buildingsOnLocation.get(buildingOrder));
            }
        }
    }
    
    private void ProcessBuildingAction(BuildingType type, int level){
        if(type == BuildingType.FARM){
            if(!unitsOnLocation.containsKey(curPlayer)){
                unitsOnLocation.put(curPlayer, new HashMap<UnitController.UnitType, Integer>());
            }
            
            if(!unitsOnLocation.get(curPlayer).containsKey(UnitController.UnitType.PEASANT)){
                unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PEASANT, 0);
            }
            int newCount = unitsOnLocation.get(curPlayer).get(UnitController.UnitType.PEASANT)
                    + level * data.weight * Constants.FARM_MULTIPLICATOR;
            
            unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PEASANT, newCount);
        }
        
        if(type == BuildingType.BARRACK){
            if(!unitsOnLocation.containsKey(curPlayer)){
                unitsOnLocation.put(curPlayer, new HashMap<UnitController.UnitType, Integer>());
            }
            
            if(!unitsOnLocation.get(curPlayer).containsKey(UnitController.UnitType.PEASANT)){
                unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PEASANT, 0);
            }
            
            if(!unitsOnLocation.get(curPlayer).containsKey(UnitController.UnitType.SOLDIER)){
                unitsOnLocation.get(curPlayer).put(UnitController.UnitType.SOLDIER, 0);
            }
            int convertCount = Math.min(unitsOnLocation.get(curPlayer).get(UnitController.UnitType.PEASANT),
                    level * data.weight * Constants.BARRACK_MULTIPLICATOR);
            
            int newPeasants = unitsOnLocation.get(curPlayer).get(UnitController.UnitType.PEASANT) - convertCount;
            int newSoldiers = unitsOnLocation.get(curPlayer).get(UnitController.UnitType.SOLDIER) + convertCount;
            
            unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PEASANT, newPeasants);
            unitsOnLocation.get(curPlayer).put(UnitController.UnitType.SOLDIER, newSoldiers);
        }
        
        if(type == BuildingType.TEMPLE){
            if(!unitsOnLocation.containsKey(curPlayer)){
                unitsOnLocation.put(curPlayer, new HashMap<UnitController.UnitType, Integer>());
            }
            
            if(!unitsOnLocation.get(curPlayer).containsKey(UnitController.UnitType.PEASANT)){
                unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PEASANT, 0);
            }
            
            if(!unitsOnLocation.get(curPlayer).containsKey(UnitController.UnitType.PRIEST)){
                unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PRIEST, 0);
            }
            int convertCount = Math.min(unitsOnLocation.get(curPlayer).get(UnitController.UnitType.PEASANT),
                    level * data.weight * Constants.BARRACK_MULTIPLICATOR);
            
            int newPeasants = unitsOnLocation.get(curPlayer).get(UnitController.UnitType.PEASANT) - convertCount;
            int newPriests = unitsOnLocation.get(curPlayer).get(UnitController.UnitType.PRIEST) + convertCount;
            
            unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PEASANT, newPeasants);
            unitsOnLocation.get(curPlayer).put(UnitController.UnitType.PRIEST, newPriests);
        }
    }
}
