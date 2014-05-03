/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.latestagedesign.religiouswars.control.field;

import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;

/**
 *
 * @author Greg
 */
public class UnitController {
    public enum UnitType{
        PEASANT,
        SOLDIER,
        PRIEST
    }
    
    public static UnitType GetUnitTypeByBuildingType(VOFieldLocation.BuildingType type){
        if(type == VOFieldLocation.BuildingType.BARRACK) return UnitType.SOLDIER;
        if(type == VOFieldLocation.BuildingType.FARM) return UnitType.PEASANT;
        if(type == VOFieldLocation.BuildingType.TEMPLE) return UnitType.PRIEST;
        return UnitType.PEASANT;
    }
}
