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

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import java.util.ArrayList;
import java.util.HashMap;

public class UnitController {
    public enum UnitType{
        PEASANT,
        SOLDIER,
        PRIEST
    }
    
    private static int PRIEST_ACTION_NUMBER_PER_TURN = 3;
    
    private static UnitType[] PRIEST_ACTION_ORDER = {UnitType.PEASANT,
        UnitType.SOLDIER,
        UnitType.PRIEST};
    
    private static int SOLDIER_ACTION_NUMBER_PER_TURN = 5;
    
    private static UnitType[] SOLDIER_ACTION_ORDER = {UnitType.SOLDIER,
        UnitType.PEASANT,
        UnitType.PRIEST};
    
    private static int[] SOLDIER_ACTION_WEIGHT = {5,
        1,
        1};
    
    public static UnitType GetUnitTypeByBuildingType(VOFieldLocation.BuildingType type){
        if(type == VOFieldLocation.BuildingType.BARRACK) return UnitType.SOLDIER;
        if(type == VOFieldLocation.BuildingType.FARM) return UnitType.PEASANT;
        if(type == VOFieldLocation.BuildingType.TEMPLE) return UnitType.PRIEST;
        return UnitType.PEASANT;
    }
    
    public static void MoveUnits(int fromId, int toId, int count, UnitType type){
        FieldController.getinstance().curMap.GetLocationById(fromId).fLoc.ShiftUnitCount(PlayersController.getinstance().GetCurrentPlayerId(), type, -count);
        FieldController.getinstance().curMap.GetLocationById(toId).fLoc.ShiftUnitCount(PlayersController.getinstance().GetCurrentPlayerId(), type, count);
    }
    
    public static void ProcessUnitsActions(VOFieldLocation loc){
        // PROCESS SOLDIERS ACTION
        ArrayList<Integer> soldiersOwners = loc.GetUnitsOfTypeOwners(UnitType.SOLDIER);
        
        // OWNER PROCESS
        if(loc.curOwnerId != -1 && loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.SOLDIER) > 0){
            int sCount = loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.SOLDIER);
            
            HashMap<UnitType, Integer> units = new HashMap<UnitType, Integer>();
            
            units.put(UnitType.SOLDIER, loc.GetUnitTypeCount(UnitType.SOLDIER) - sCount);
            int prModifier = loc.unitsOnLocation.get(loc.curOwnerId).containsKey(UnitType.PRIEST) ? loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.PRIEST) : 0;
            units.put(UnitType.PRIEST, loc.GetUnitTypeCount(UnitType.PRIEST) - prModifier);
            int peModifier = loc.unitsOnLocation.get(loc.curOwnerId).containsKey(UnitType.PEASANT) ? loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.PEASANT) : 0;
            units.put(UnitType.PEASANT, loc.GetUnitTypeCount(UnitType.PEASANT) - peModifier);

            for(int i = 0; i < sCount; i++){
                if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) <= 0) break;
                int weightCount = SOLDIER_ACTION_NUMBER_PER_TURN;
                while(weightCount > 0){
                    if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                        for(int j = 0; j < SOLDIER_ACTION_ORDER.length; j++){
                            if(units.get(SOLDIER_ACTION_ORDER[j]) > 0){
                                loc.ShiftUnitCountNotOwner(loc.curOwnerId, SOLDIER_ACTION_ORDER[j], 1);
                                weightCount -= SOLDIER_ACTION_WEIGHT[j];
                                units.put(SOLDIER_ACTION_ORDER[j], units.get(SOLDIER_ACTION_ORDER[j]) - 1);
                                j = SOLDIER_ACTION_ORDER.length + 1;
                            }
                        }
                    }
                    else weightCount = 0;
                }
                loc.ShiftUnitCount(loc.curOwnerId, UnitType.SOLDIER, -1);
            }
        }
        
        
        // OTHER PROCESS
        for(Integer i : soldiersOwners){
            if(i != loc.curOwnerId){
                int sCount = loc.unitsOnLocation.get(i).get(UnitType.SOLDIER);

                HashMap<UnitType, Integer> units = new HashMap<UnitType, Integer>();

                units.put(UnitType.SOLDIER, loc.GetUnitTypeCount(UnitType.SOLDIER) - sCount);
                int prModifier = loc.unitsOnLocation.get(i).containsKey(UnitType.PRIEST) ? loc.unitsOnLocation.get(i).get(UnitType.PRIEST) : 0;
                units.put(UnitType.PRIEST, loc.GetUnitTypeCount(UnitType.PRIEST) - prModifier);
                int peModifier = loc.unitsOnLocation.get(i).containsKey(UnitType.PEASANT) ? loc.unitsOnLocation.get(i).get(UnitType.PEASANT) : 0;
                units.put(UnitType.PEASANT, loc.GetUnitTypeCount(UnitType.PEASANT) - peModifier);

                for(int k = 0; k < sCount; k++){
                    if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                        int weightCount = SOLDIER_ACTION_NUMBER_PER_TURN;
                        while(weightCount > 0){
                            if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                                for(int j = 0; j < SOLDIER_ACTION_ORDER.length; j++){
                                    boolean flag = true;
                                    if(flag && units.get(SOLDIER_ACTION_ORDER[j]) > 0){
                                        loc.ShiftUnitCountNotOwner(i, SOLDIER_ACTION_ORDER[j], 1);
                                        weightCount -= SOLDIER_ACTION_WEIGHT[j];
                                        units.put(SOLDIER_ACTION_ORDER[j], units.get(SOLDIER_ACTION_ORDER[j]) - 1);
                                        flag = false;
                                    }
                                }
                            }
                            else
                                weightCount = 0;
                        }
                        loc.ShiftUnitCount(i, UnitType.SOLDIER, -1);
                    }
                }
            }
        }
        
        // PROCESS PRIESTS ACTION
        ArrayList<Integer> priestsOwners = loc.GetUnitsOfTypeOwners(UnitType.PRIEST);
        
        // OWNER PROCESS
        if(loc.curOwnerId != -1 && loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.PRIEST) > 0){
            int pCount = loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.PRIEST);
            
            HashMap<UnitType, Integer> units = new HashMap<UnitType, Integer>();
            
            int slModifier = loc.unitsOnLocation.get(loc.curOwnerId).containsKey(UnitType.SOLDIER) ? loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.SOLDIER) : 0;
            units.put(UnitType.SOLDIER, loc.GetUnitTypeCount(UnitType.SOLDIER) - slModifier);
            int prModifier = loc.unitsOnLocation.get(loc.curOwnerId).containsKey(UnitType.PRIEST) ? loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.PRIEST) : 0;
            units.put(UnitType.PRIEST, loc.GetUnitTypeCount(UnitType.PRIEST) - prModifier);
            int peModifier = loc.unitsOnLocation.get(loc.curOwnerId).containsKey(UnitType.PEASANT) ? loc.unitsOnLocation.get(loc.curOwnerId).get(UnitType.PEASANT) : 0;
            units.put(UnitType.PEASANT, loc.GetUnitTypeCount(UnitType.PEASANT) - peModifier);

            for(int i = 0; i < pCount; i++){
                if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                    int weightCount = PRIEST_ACTION_NUMBER_PER_TURN;
                    while(weightCount > 0){
                        if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                            for(int j = 0; j < SOLDIER_ACTION_ORDER.length; j++){
                                if(units.get(PRIEST_ACTION_ORDER[j]) > 0){
                                    loc.ShiftUnitCountNotOwner(loc.curOwnerId, PRIEST_ACTION_ORDER[j], 1);
                                    loc.ShiftUnitCount(loc.curOwnerId, PRIEST_ACTION_ORDER[j], 1);
                                    weightCount -= 1;
                                    units.put(PRIEST_ACTION_ORDER[j], units.get(PRIEST_ACTION_ORDER[j]) - 1);
                                    j = PRIEST_ACTION_ORDER.length + 1;
                                }
                            }
                        }
                        else weightCount = 0;
                    }
                }
            }
        }
        
        
        // OTHER PROCESS
        for(Integer i : priestsOwners){
            if(i != loc.curOwnerId){
                int pCount = loc.unitsOnLocation.get(i).get(UnitType.PRIEST);
            
                HashMap<UnitType, Integer> units = new HashMap<UnitType, Integer>();

                int slModifier = loc.unitsOnLocation.get(i).containsKey(UnitType.SOLDIER) ? loc.unitsOnLocation.get(i).get(UnitType.SOLDIER) : 0;
                units.put(UnitType.SOLDIER, loc.GetUnitTypeCount(UnitType.SOLDIER) - slModifier);
                int prModifier = loc.unitsOnLocation.get(i).containsKey(UnitType.PRIEST) ? loc.unitsOnLocation.get(i).get(UnitType.PRIEST) : 0;
                units.put(UnitType.PRIEST, loc.GetUnitTypeCount(UnitType.PRIEST) - prModifier);
                int peModifier = loc.unitsOnLocation.get(i).containsKey(UnitType.PEASANT) ? loc.unitsOnLocation.get(i).get(UnitType.PEASANT) : 0;
                units.put(UnitType.PEASANT, loc.GetUnitTypeCount(UnitType.PEASANT) - peModifier);
                
                for(int k = 0; k < pCount; k++){
                    if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                        int weightCount = PRIEST_ACTION_NUMBER_PER_TURN;
                        while(weightCount > 0){
                            if(units.get(UnitType.SOLDIER) + units.get(UnitType.PRIEST) + units.get(UnitType.PEASANT) > 0){
                                for(int j = 0; j < SOLDIER_ACTION_ORDER.length; j++){
                                    if(units.get(PRIEST_ACTION_ORDER[j]) > 0){
                                        loc.ShiftUnitCountNotOwner(i, PRIEST_ACTION_ORDER[j], 1);
                                        loc.ShiftUnitCount(i, PRIEST_ACTION_ORDER[j], 1);
                                        weightCount -= 1;
                                        units.put(PRIEST_ACTION_ORDER[j], units.get(PRIEST_ACTION_ORDER[j]) - 1);
                                        j = PRIEST_ACTION_ORDER.length + 1;
                                    }
                                }
                            }
                            else weightCount = 0;
                        }
                    }
                }
            }
        }
        
        
    }
}
