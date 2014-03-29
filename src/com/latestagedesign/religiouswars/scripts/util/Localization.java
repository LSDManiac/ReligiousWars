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

package com.latestagedesign.religiouswars.scripts.util;

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
        langs.put("RU", new HashMap<String, String>());
        langs.get("RU").put("#religious_wars", "Религиозные Войны");
        
        return langs;
    }
}
