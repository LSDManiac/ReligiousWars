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
import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.sun.javafx.geom.Vec2f;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FieldCreator {
    
    private static class LocationsNode{
        List<LocationsNode> neighbourNodes;
        List<VOLocation> neighbourLocations;
        int toBorderCount;
        List<Vec2f> toPordersPoints;
        Vec2f point;
    }
    
    private static Map<VOMap.MapSize, VOMap> MapGraphs;
    
    public static void CreateMap(int playerNum, VOMap.MapSize size){
        VOMap map = CreateMapGraph(size);
        
        FieldController.getinstance().curMap = map;
        
        FieldController.getinstance().OnMapLoadingComplete();
    }
    
    private static VOMap CreateMapGraph(VOMap.MapSize size){
        if(MapGraphs == null) MapGraphs = new HashMap<VOMap.MapSize, VOMap>();
        if(!MapGraphs.containsKey(size)){
            VOMap map = LoadMap(GetMapNameBySize(size));
            MapGraphs.put(size, map);
        }
        
        return MapGraphs.get(size).Clone();
    }
    
    private static String GetMapNameBySize(VOMap.MapSize size){
        switch(size){
            case GERMANY:
                return "Location1";
            case UKRAINE:
                return "Location1";
            case USA:
                return "Location1";
        }
        
        return "Location1";
    }
    
    private static VOMap LoadMap(String mapName){
        VOMap map = new VOMap();
        
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            
            InputStream is = FieldCreator.class.getResourceAsStream("/com/latestagedesign/religiouswars/recources/" + mapName + ".xml");
            
            Document document = builder.parse(is);
            
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("location");
            
            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                
                int id = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
                int weight = Integer.parseInt(node.getAttributes().getNamedItem("weight").getNodeValue());
                
                ArrayList<Pair<Vec2f, Vec2f>> lines = new ArrayList<Pair<Vec2f, Vec2f>>();
                
                NodeList subNodeList = node.getChildNodes();
                
                List<Integer> neighbours = new ArrayList<Integer>();
                
                for(int j = 0; j < subNodeList.getLength(); j++){
                    Node cNode = subNodeList.item(j);
                    
                    String value = cNode.getTextContent().trim();
                    String name = cNode.getNodeName();
                    
                    if(name == "heighbour"){
                        Integer val_int = Integer.parseInt(value);
                        if(!neighbours.contains(val_int))
                            neighbours.add(val_int);
                    }
                    else if(name == "border"){
                        float x1 = Float.parseFloat(cNode.getAttributes().getNamedItem("x1").getNodeValue());
                        float y1 = Float.parseFloat(cNode.getAttributes().getNamedItem("y1").getNodeValue());
                        float x2 = Float.parseFloat(cNode.getAttributes().getNamedItem("x2").getNodeValue());
                        float y2 = Float.parseFloat(cNode.getAttributes().getNamedItem("y2").getNodeValue());
                        
                        lines.add(new Pair<Vec2f, Vec2f>(new Vec2f(x1, y1), new Vec2f(x2, y2)));
                    }
                }
                
                map.AddLocation(id, neighbours, weight, lines);
            }
        }
        catch(Exception e){
            System.out.println("FieldCreator Error: " + e.getMessage() + "\n" + e.toString());
        }
        
        return map;
    }
}
