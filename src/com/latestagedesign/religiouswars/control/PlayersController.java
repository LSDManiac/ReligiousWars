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

package com.latestagedesign.religiouswars.control;

import com.latestagedesign.religiouswars.model.VOClasses.VOPlayer;
import java.awt.Color;
import java.util.ArrayList;

public class PlayersController {
    
    private static PlayersController _instance;
    public static PlayersController getinstance(){
        if(_instance == null) _instance = new PlayersController();
        return _instance;
    }
    
    private Color[] playersColor = {Color.red, Color.blue, Color.yellow, Color.cyan, Color.orange, Color.magenta, Color.pink};
    
    public PlayersController(){}
    
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
            curPlayers.add(pl);
        }
    }
    
    public Color GetPlayerColorById(int id){
        for(VOPlayer pl : curPlayers){
            if(pl.id == id)
                return pl.playerColor;
        }
        return Color.gray;
    }
    
    public int getCurrentPlayerId(){
        return curPlayerId;
    }
    
    public void StepToNextPlayer(){
        for(int i = 0; i < curPlayers.size(); i++){
            if(curPlayers.get(i).id == curPlayerId){
                if(i == curPlayers.size() - 1){
                    curPlayerId = curPlayers.get(0).id;
                    return;
                }
                else{
                    curPlayerId = curPlayers.get(i + 1).id;
                    return;
                }
            }
        }
    }
    
    public int GetPlayerIdOnPos(int pos){
        return curPlayers.get(pos).id;
    }
    
    
}
