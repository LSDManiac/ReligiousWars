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

import com.latestagedesign.religiouswars.control.exceptions.InitializationException;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.GameField;

public class FieldController {
    
    private static FieldController instance;
    public static FieldController getinstance(){
        if(instance == null) instance = new FieldController();
        return instance;
    }
    
    private GameField field;
    private Boolean isInited = false;
    
    public FieldController(){
    }
    
    public void Init(GameField _field) throws InitializationException{
        if(isInited)
            throw new InitializationException(InitializationException.ExceptionType.FIELD_CONTROLLER_ALREADY_INITED);
        
        
    }
}
