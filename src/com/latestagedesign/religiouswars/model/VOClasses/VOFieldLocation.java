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
    
    public ArrayList<Integer> GetUnitsOfTypeOwners(UnitController.UnitType type){
        ArrayList<Integer> own = new ArrayList<Integer>();
        
        for(Integer i : unitsOnLocation.keySet()){
            if(unitsOnLocation.get(i).containsKey(type) && unitsOnLocation.get(i).get(type) > 0)
                own.add(i);
        }
        
        return own;
    }
    
    public int GetUnitTypeCount(UnitController.UnitType type){
        int count = 0;
        
        for(Integer i : unitsOnLocation.keySet()){
            if(unitsOnLocation.get(i).containsKey(type))
                count += unitsOnLocation.get(i).get(type);
        }
        
        return count;
    }
    
    public void BuildOnLocation(BuildingType type){
        if(!buildingsOnLocation.containsKey(type)){
            buildingsOnLocation.put(type, 1);
        }
        else{
            buildingsOnLocation.put(type, buildingsOnLocation.get(type) + 1);
        }
    }
    
    public void OnTurn(){
        UnitController.ProcessUnitsActions(this);
        RecountOwner();
        if(curOwnerId != -1){
            for (BuildingType buildingOrder : buildingOrder) {
                if (buildingsOnLocation.containsKey(buildingOrder)) {
                    ProcessBuildingAction(buildingOrder, buildingsOnLocation.get(buildingOrder));
                }
            }
        }
    }
    
    private void RecountOwner(){
        if(unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT) <= 0){
            int id = -1;
            int maxCount = -1;
            for(Integer i : unitsOnLocation.keySet()){
                int totalCount = 0;
                totalCount += unitsOnLocation.get(i).containsKey(UnitController.UnitType.PRIEST) ? unitsOnLocation.get(i).get(UnitController.UnitType.PRIEST) : 0;
                totalCount += unitsOnLocation.get(i).containsKey(UnitController.UnitType.SOLDIER) ? unitsOnLocation.get(i).get(UnitController.UnitType.SOLDIER) : 0;
                
                if(totalCount > maxCount){
                    id = i;
                    maxCount = totalCount;
                }
            }
            
            setcurOwnerId(id);
        }
    }
    
    private void ProcessBuildingAction(BuildingType type, int level){
        if(type == BuildingType.FARM){
            int newCount = level * getMultiplierByBuildingType(type);
            
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PEASANT, newCount);
        }
        
        if(type == BuildingType.BARRACK){
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PEASANT, 0);
            ShiftUnitCount(curOwnerId, UnitController.UnitType.SOLDIER, 0);
            
            int convertCount = Math.min(unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT),
                    level * getMultiplierByBuildingType(type));
            
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PEASANT, -convertCount);
            ShiftUnitCount(curOwnerId, UnitController.UnitType.SOLDIER, convertCount);
        }
        
        if(type == BuildingType.TEMPLE){
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PEASANT, 0);
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PRIEST, 0);
            
            int convertCount = Math.min(unitsOnLocation.get(curOwnerId).get(UnitController.UnitType.PEASANT),
                    level * getMultiplierByBuildingType(type));
            
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PEASANT, -convertCount);
            ShiftUnitCount(curOwnerId, UnitController.UnitType.PRIEST, convertCount);
        }
    }
    
    public int getMultiplierByBuildingType(BuildingType t){
        //System.out.println(t + " " + data.weight);
        if(t == BuildingType.BARRACK) return data.weight * Constants.BARRACK_MULTIPLICATOR;
        if(t == BuildingType.FARM) return data.weight * Constants.FARM_MULTIPLICATOR;
        if(t == BuildingType.TEMPLE) return data.weight * Constants.TEMPLE_MULTIPLICATOR;
        return data.weight;
    }
    
    public void ShiftUnitCount(int owner, UnitController.UnitType type, int count){
        if(!unitsOnLocation.containsKey(owner)){
            unitsOnLocation.put(owner, new HashMap<UnitController.UnitType, Integer>());
        }
        if(!unitsOnLocation.get(owner).containsKey(type)){
            unitsOnLocation.get(owner).put(type, 0);
        }
        unitsOnLocation.get(owner).put(type, unitsOnLocation.get(owner).get(type) + count);
        
        if(unitsOnLocation.get(owner).get(type) < 0){
            unitsOnLocation.get(owner).put(type, 0);
        }
    }
    
    public void ShiftUnitCountNotOwner(int owner, UnitController.UnitType type, int count){
        ArrayList owners = GetUnitsOfTypeOwners(type);
        if(owners.contains(owner)){
            owners.remove(owners.indexOf(owner));
        }
        
        int rand = (int)Math.floor(Math.random() * owners.size());
        
        int available = unitsOnLocation.get(owners.get(rand)).get(type);
        if(available >= count){
            unitsOnLocation.get(owners.get(rand)).put(type, available - count);
        }
        else{
            unitsOnLocation.get(owners.get(rand)).put(type, 0);
            ShiftUnitCountNotOwner(owner, type, count - available);
        }
        
    }
}
