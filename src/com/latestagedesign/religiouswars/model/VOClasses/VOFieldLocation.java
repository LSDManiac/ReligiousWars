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

import com.latestagedesign.religiouswars.control.PlayersController;
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
    
    public Color curColor = Constants.NOBODY_PROVINCE_COLOR;
    
    public int curOwnerId = -1;
    
    public void setcurOwnerId(int id){
        curOwnerId = id;
        curColor = PlayersController.getinstance().GetTerritoryPlayerColorById(id);
        if(!unitsOnLocation.containsKey(id)){
            unitsOnLocation.put(id, new HashMap<UnitController.UnitType, Integer>());
        }
        if(!unitsOnLocation.get(id).containsKey(UnitController.UnitType.PEASANT)){
            unitsOnLocation.get(id).put(UnitController.UnitType.PRIEST, 0);
        }
        if(!unitsOnLocation.get(id).containsKey(UnitController.UnitType.PRIEST)){
            unitsOnLocation.get(id).put(UnitController.UnitType.PRIEST, 0);
        }
        if(!unitsOnLocation.get(id).containsKey(UnitController.UnitType.SOLDIER)){
            unitsOnLocation.get(id).put(UnitController.UnitType.SOLDIER, 0);
        }
    }
    
    public VOFieldLocation(){
        unitsOnLocation = new HashMap<Integer, HashMap<UnitController.UnitType, Integer>>();
        buildingsOnLocation = new HashMap<BuildingType, Integer>();
    }
    
    public HashMap<Integer, HashMap<UnitController.UnitType, Integer>> unitsOnLocation;
    
    public HashMap<BuildingType, Integer> buildingsOnLocation;
    
    
    public void BuildOnLocation(BuildingType type){
        if(!buildingsOnLocation.containsKey(type)){
            buildingsOnLocation.put(type, 1);
        }
        else{
            buildingsOnLocation.put(type, buildingsOnLocation.get(type) + 1);
        }
    }
    
    public void OnTurn(){
        for (BuildingType buildingOrder : buildingOrder) {
            if (buildingsOnLocation.containsKey(buildingOrder)) {
                ProcessBuildingAction(buildingOrder, buildingsOnLocation.get(buildingOrder));
            }
        }
    }
    
    private void ProcessBuildingAction(BuildingType type, int level){
        if(type == BuildingType.FARM){
            if(!unitsOnLocation.containsKey(curOwnerId)){
                unitsOnLocation.put(curOwnerId, new HashMap<UnitController.UnitType, Integer>());
            }
            
            if(!unitsOnLocation.get(curOwnerId).containsKey(UnitController.UnitType.PEASANT)){
                unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PEASANT, 0);
            }
            int newCount = unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT)
                    + level * getMultiplierByBuildingType(type);
            
            unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PEASANT, newCount);
        }
        
        if(type == BuildingType.BARRACK){
            if(!unitsOnLocation.containsKey(curOwnerId)){
                unitsOnLocation.put(curOwnerId, new HashMap<UnitController.UnitType, Integer>());
            }
            
            if(!unitsOnLocation.get(curOwnerId).containsKey(UnitController.UnitType.PEASANT)){
                unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PEASANT, 0);
            }
            
            if(!unitsOnLocation.get(curOwnerId).containsKey(UnitController.UnitType.SOLDIER)){
                unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.SOLDIER, 0);
            }
            int convertCount = Math.min(unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT),
                    level * getMultiplierByBuildingType(type));
            
            int newPeasants = unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT) - convertCount;
            int newSoldiers = unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.SOLDIER) + convertCount;
            
            unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PEASANT, newPeasants);
            unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.SOLDIER, newSoldiers);
        }
        
        if(type == BuildingType.TEMPLE){
            if(!unitsOnLocation.containsKey(curOwnerId)){
                unitsOnLocation.put(curOwnerId, new HashMap<UnitController.UnitType, Integer>());
            }
            
            if(!unitsOnLocation.get(curOwnerId).containsKey(UnitController.UnitType.PEASANT)){
                unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PEASANT, 0);
            }
            
            if(!unitsOnLocation.get(curOwnerId).containsKey(UnitController.UnitType.PRIEST)){
                unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PRIEST, 0);
            }
            int convertCount = Math.min(unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT),
                    level * getMultiplierByBuildingType(type));
            
            int newPeasants = unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT) - convertCount;
            int newPriests = unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PRIEST) + convertCount;
            
            unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PEASANT, newPeasants);
            unitsOnLocation.get(curOwnerId).put(UnitController.UnitType.PRIEST, newPriests);
        }
    }
    
    public int getMultiplierByBuildingType(BuildingType t){
        //System.out.println(t + " " + data.weight);
        if(t == BuildingType.BARRACK) return data.weight * Constants.BARRACK_MULTIPLICATOR;
        if(t == BuildingType.FARM) return data.weight * Constants.FARM_MULTIPLICATOR;
        if(t == BuildingType.TEMPLE) return data.weight * Constants.TEMPLE_MULTIPLICATOR;
        return data.weight;
    }
}
