package com.latestagedesign.religiouswars.model;

import java.util.HashMap;
import java.util.Map;

public class Localization {
    private static Map<String, Map<String, String>> languages;
    private static String location = "RU";
    
    public static String Get(String key){
        if(languages == null) languages = ReadLanguages();
        if(languages.containsKey(location)){
            if(languages.get(location).containsKey(key))
                return languages.get(location).get(key);
        }
        return key;
    }
    
    private static Map<String, Map<String, String>> ReadLanguages(){
        Map<String, Map<String, String>> langs = new HashMap<String, Map<String, String>>();
        langs.put("EN", new HashMap<String, String>());
        langs.get("EN").put("#religious_wars", "Religious Wars");
        langs.get("EN").put("#top_info_bar_text", "Now %name%'s turn");
        langs.get("EN").put("#send", "Send");
        langs.get("EN").put("#no_such_unit_on_location", "No such unit on location");
        langs.get("EN").put("#custom_start", "Custom start");
        langs.get("EN").put("#help", "Help");
        langs.get("EN").put("#quick_start", "New game");
        langs.get("EN").put("#resume", "Resume");
        langs.get("EN").put("#back", "Back");
        langs.get("EN").put("#start", "Start");
        langs.get("EN").put("#map_size", "Map size");
        langs.get("EN").put("#players_num", "Players");
        
        langs.put("RU", new HashMap<String, String>());
        langs.get("RU").put("#religious_wars", "Религиозные Войны");
        langs.get("RU").put("#top_info_bar_text", "Ход игрока %name%");
        langs.get("RU").put("#send", "Послать");
        langs.get("RU").put("#no_such_unit_on_location", "В локации нет юнитов данного типа");
        langs.get("RU").put("#custom_start", "Новая игра");
        langs.get("RU").put("#help", "Помощь");
        langs.get("RU").put("#quick_start", "Быстрый старт");
        langs.get("RU").put("#resume", "Продолжить");
        langs.get("RU").put("#back", "Назад");
        langs.get("RU").put("#start", "Старт");
        langs.get("RU").put("#map_size", "Размер карты");
        langs.get("RU").put("#players_num", "Игроки");
        
        return langs;
    }
}
