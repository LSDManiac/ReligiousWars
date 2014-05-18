package com.latestagedesign.religiouswars.control;

import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.Constants;
import com.latestagedesign.religiouswars.model.VOClasses.VOPlayer;
import java.awt.Color;
import java.util.ArrayList;

public class PlayersController {
    
    private static PlayersController _instance;
    public static PlayersController getinstance(){
        if(_instance == null) _instance = new PlayersController();
        return _instance;
    }
    
    private Color[] playersColor = {
        new Color(255, 0, 0),
        new Color(0, 255, 0),
        new Color(0, 0, 255),
        new Color(255, 255, 0),
        new Color(255, 0, 255),
        new Color(0, 255, 255)};
    
    private Color[] playersTerritoryColor = {
        new Color(255, 215, 215),
        new Color(215, 255, 215),
        new Color(215, 215, 255),
        new Color(255, 255, 215),
        new Color(255, 215, 255),
        new Color(215, 255, 255)};
    
    public Color GetTerritoryNumeredColor(int i){
        if(i > -1 && i < playersTerritoryColor.length){
            return playersTerritoryColor[i];
        }
        return Constants.NOBODY_PROVINCE_COLOR;
    }
    
    public PlayersController(){
        curPlayers = new ArrayList<VOPlayer>();
    }
    
    private ArrayList<VOPlayer> curPlayers;
    
    private int curPlayerId;
    
    public int GetPlayersNum(){
        return curPlayers.size();
    }
    
    public void CreatePlayers(int num){
        curPlayers = new ArrayList<VOPlayer>();
        for(int i = 0; i < num; i++){
            VOPlayer pl = new VOPlayer();
            pl.id = i;
            pl.name = "Player" + (i + 1);
            pl.playerColor = playersColor[i];
            pl.playerTerritoryColor = playersTerritoryColor[i];
            curPlayers.add(pl);
        }
    }
    
    public Color GetPlayerColorById(int id){
        for(VOPlayer pl : curPlayers){
            if(pl.id == id)
                return pl.playerColor;
        }
        return Constants.NOBODY_PROVINCE_COLOR;
    }
    
    public Color GetTerritoryPlayerColorById(int id){
        for(VOPlayer pl : curPlayers){
            if(pl.id == id)
                return pl.playerTerritoryColor;
        }
        return Constants.NOBODY_PROVINCE_COLOR;
    }
    
    public Color GetCurrentPlayerColor(){
        for(VOPlayer pl : curPlayers){
            if(pl.id == curPlayerId)
                return pl.playerColor;
        }
        return Constants.NOBODY_PROVINCE_COLOR;
    }
    
    public int GetCurrentPlayerId(){
        return curPlayerId;
    }
    
    public String GetCurrentPlayerName(){
        for(VOPlayer p : curPlayers){
            if(p.id == curPlayerId) return p.name;
        }
        
        return "";
    }
    
    public void StepToNextPlayer(){
        int prevPlayer = curPlayerId;
        int newPlayerId = -1;
        for(int i = 0; i < curPlayers.size(); i++){
            if(newPlayerId == -1){
                if(curPlayers.get(i).id == curPlayerId){
                    if(i == curPlayers.size() - 1){
                        newPlayerId = curPlayers.get(0).id;
                    }
                    else{
                        newPlayerId = curPlayers.get(i + 1).id;
                    }
                }
            }
        }
        curPlayerId = newPlayerId;
        if(FieldController.getinstance().GetPlayerProvinceCount(curPlayerId) <= 0)
            StepToNextPlayer();
        
        //if(curPlayerId == prevPlayer);
        //    FieldController.getinstance().FireWin();
    }
    
    public int GetPlayerIdOnPos(int pos){
        return curPlayers.get(pos).id;
    }
}
