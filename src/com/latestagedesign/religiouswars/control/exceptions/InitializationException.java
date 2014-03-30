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

package com.latestagedesign.religiouswars.control.exceptions;

public class InitializationException extends Exception {
    public enum ExceptionType{
        NONE,
        FIELD_CONTROLLER_ALREADY_INITED,
        FIELD_CONTROLLER_NOT_INITED
    }
    
    public InitializationException() {}
    
    public InitializationException(ExceptionType type){
        super("InitializationException:" + GetErrorStringByType(type));
    }
    
    private static String GetErrorStringByType(ExceptionType ex){
        switch(ex){
            case FIELD_CONTROLLER_ALREADY_INITED:
                return "Tried to init FieldController second time";
            case FIELD_CONTROLLER_NOT_INITED:
                return "Tried to use not inited FieldController";
        }
        return "ERROR IN ERROR";
    }
}
