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
import com.latestagedesign.religiouswars.control.exceptions.InitializationException;
import com.latestagedesign.religiouswars.model.Constants;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.GameField;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FieldController {
    
    private static FieldController instance;
    public static FieldController getinstance(){
        if(instance == null) instance = new FieldController();
        return instance;
    }
    
    public boolean inField = false;
    
    public enum ControllerStates{
        NOTHING,
        BUILDING,
        ATTACKING
    }
    
    private GameField field;
    private Boolean isInited = false;
    
    public ControllerStates curState = ControllerStates.NOTHING;
    
    public VOMap curMap;
    
    public int selectedBuildLocation;
    public int selectedAttackedLocation;
    public int selectedAttackingLocation;
    
    public FieldController(){}
    
    public void Init(GameField _field) throws InitializationException{
        if(isInited)
            throw new InitializationException(InitializationException.ExceptionType.FIELD_CONTROLLER_ALREADY_INITED);
        this.field = _field;
        
    }
    
    public void CreateField(int playerNum, VOMap.MapSize mapSize){
        FieldCreator.CreateMap(playerNum, mapSize);
    }
    
    public void OnMapLoadingComplete(){
        field.fieldLocations = new ArrayList<VOFieldLocation>();
        for(VOLocation l : curMap.locations){
            field.fieldLocations.add(l.fLoc);
            l.fLoc.buildingsOnLocation = new HashMap<VOFieldLocation.BuildingType, Integer>();
            l.fLoc.buildingsOnLocation.put(VOFieldLocation.BuildingType.FARM, 1);
            l.fLoc.buildingsOnLocation.put(VOFieldLocation.BuildingType.BARRACK, 1);
            l.fLoc.buildingsOnLocation.put(VOFieldLocation.BuildingType.TEMPLE, 1);
        }
        
        RandomizePlayersPosition();
        
        for(VOFieldLocation l : field.fieldLocations){
            l.unitsOnLocation = new HashMap<Integer, HashMap<UnitController.UnitType, Integer>>();
            if(l.curOwnerId != -1){
                l.unitsOnLocation.put(l.curOwnerId, new HashMap<UnitController.UnitType, Integer>());
                
                int startPeasants = Constants.START_PEASANT_NUMBER * l.data.weight;
                int startPriests = Constants.START_PRIEST_NUMBER * l.data.weight;
                int startSoldiers = Constants.START_SOLDIER_NUMBER * l.data.weight;
                
                l.unitsOnLocation.get(l.curOwnerId).put(UnitController.UnitType.PRIEST, startPriests);
                l.unitsOnLocation.get(l.curOwnerId).put(UnitController.UnitType.PEASANT, startPeasants);
                l.unitsOnLocation.get(l.curOwnerId).put(UnitController.UnitType.SOLDIER, startSoldiers);
            }
            else{
                l.unitsOnLocation.put(l.curOwnerId, new HashMap<UnitController.UnitType, Integer>());
                
                int startPeasants = Constants.START_EMPTY_PEASANT_NUMBER * l.data.weight;
                
                l.unitsOnLocation.get(l.curOwnerId).put(UnitController.UnitType.PEASANT, startPeasants);
                l.unitsOnLocation.get(l.curOwnerId).put(UnitController.UnitType.SOLDIER, 0);
                l.unitsOnLocation.get(l.curOwnerId).put(UnitController.UnitType.PRIEST, 0);
            }
        }
        
        inField = true;
        BotBar.getinstance().RecountState();
    }
    
    private void RandomizePlayersPosition(){
        for(int i = 0; i < PlayersController.getinstance().GetPlayersNum(); i++){
            int locId = GetRandomFreeStartLocationId();
            if(locId != -1)
                curMap.GetLocationById(locId).fLoc.setcurOwnerId(PlayersController.getinstance().GetPlayerIdOnPos(i));
        }
    }
    
    public int GetPlayerProvinceCount(int id){
        int c = 0;
        for(VOLocation l : curMap.locations){
            if(l.fLoc.curOwnerId == id)
                c++;
        }
        return c;
    }
    
    private int GetRandomFreeStartLocationId(){
        ArrayList<Integer> freeLocs = new ArrayList<Integer>();
        for(VOLocation l : curMap.locations){
            //System.out.println(l.isStart + " " + l.fLoc.curOwnerId);
            if(l.isStart && l.fLoc.curOwnerId == -1){
                freeLocs.add(l.id);
            }
        }
        
        if(freeLocs.isEmpty())
            return -1;
        
        int num = (int)Math.floor(Math.random() * freeLocs.size());
        
        return freeLocs.get(num);
    }
    
    public void SetLocationsSelected(int mDownLocId, int mUpLocId){
        if(mDownLocId == -1 || mUpLocId == -1 || (curState != ControllerStates.NOTHING && mDownLocId == mUpLocId)
                || (curMap.GetLocationById(mDownLocId).fLoc.curOwnerId != PlayersController.getinstance().GetCurrentPlayerId())){
            SetAllLocationsUnselected();
            field.repaint();
            BotBar.getinstance().RecountState();
            return;
        }
        
        if(mDownLocId == mUpLocId){
            curState = ControllerStates.BUILDING;
            selectedBuildLocation = mDownLocId;
        }
        else{
            curState = ControllerStates.ATTACKING;
            if(curMap.GetLocationById(mDownLocId).GetNeighbourById(mUpLocId) != null){
                selectedAttackingLocation = mDownLocId;
                selectedAttackedLocation = mUpLocId;
            }
            else{
                SetAllLocationsUnselected();
            }
        }
        
        BotBar.getinstance().RecountState();
        field.repaint();
    }
    
    public void SetAllLocationsUnselected(){
        curState = ControllerStates.NOTHING;
        selectedBuildLocation = -1;
        selectedAttackedLocation = -1;
        selectedAttackingLocation = -1;
    }
    
    public void FireBuild(VOFieldLocation.BuildingType buildingType){
        if(curState != ControllerStates.BUILDING) return;
        curMap.GetLocationById(selectedBuildLocation).fLoc.BuildOnLocation(buildingType);
        FireTurnEnded();
    }
    
    public void FireAttack(int count, UnitController.UnitType type){
        if(curState != ControllerStates.ATTACKING) return;
        UnitController.MoveUnits(selectedAttackingLocation,
                selectedAttackedLocation,
                count, type);
        FireTurnEnded();
    }
    
    public void FireTurnEnded(){
        SetAllLocationsUnselected();
        BotBar.getinstance().RecountState();
        for(VOLocation l : curMap.locations){
            l.fLoc.OnTurn();
        }
        field.repaint();
        
        PlayersController.getinstance().StepToNextPlayer();
        TopBar.getinstance().RecountState();
    }
    
    public void FireWin(){
        inField = false;
        MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.MAIN_MENU);
    }
}
