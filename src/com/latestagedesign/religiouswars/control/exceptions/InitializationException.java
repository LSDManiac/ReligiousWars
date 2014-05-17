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
