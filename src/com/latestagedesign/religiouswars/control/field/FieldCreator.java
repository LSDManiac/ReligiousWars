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

import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.sun.javafx.geom.Vec2d;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Vec2d> toPordersPoints;
        Vec2d point;
    }
    
    private static Map<VOMap.MapSize, VOMap> MapGraphs;
    
    public static VOMap CreateMap(int playerNum, VOMap.MapSize size){
        VOMap map = CreateMapGraph(size);
        
        // TODO: Set Players Start Locations
        
        // TODO: Set start locations units
        
        return map;
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
                }
                
                map.AddLocation(id, neighbours, weight);
            }
        }
        catch(Exception e){
            System.out.println("FieldCreator Error: " + e.getMessage() + "\n" + e.toString());
        }
        
        System.out.println(map);
        
        return map;
    }
    
    public static List<VOFieldLocation> CreateFieldLocations(VOMap map){
        List<VOFieldLocation> locations = new ArrayList<VOFieldLocation>();
        
        List<ArrayList<Integer>> cycles = GetAllCycles(map);
        List<LocationsNode> nodeGraph = new ArrayList<LocationsNode>();
        
        List<Vec2d> grinds = new ArrayList<Vec2d>();
        
        for(VOLocation loc : map.locations){
            for(VOLocation loc2 : loc.neighbours){
                Vec2d v = new Vec2d();
                v.x = Math.max(loc.id, loc2.id);
                v.y = Math.min(loc.id, loc2.id);
                if(!grinds.contains(v))
                    grinds.add(v);
            }
        }
        
        for(Vec2d v : grinds){
            boolean hasCycle = false;
            for(ArrayList<Integer> cycle : cycles){
                if(!cycle.contains((int)v.x) && !cycle.contains((int)v.y)){
                    hasCycle = true;
                }
            }
            if(!hasCycle){
                LocationsNode node = new LocationsNode();
                node.toBorderCount = 2;
                node.neighbourNodes = new ArrayList<LocationsNode>();
                node.neighbourLocations = new ArrayList<VOLocation>();
                
                node.neighbourLocations.add(map.GetLocationById((int)v.x));
                node.neighbourLocations.add(map.GetLocationById((int)v.y));
                
                nodeGraph.add(node);
            }
        }
        
        for(ArrayList<Integer> cycle : cycles){
            LocationsNode node = new LocationsNode();
            node.toBorderCount = 0;
            node.neighbourNodes = new ArrayList<LocationsNode>();
            node.neighbourLocations = new ArrayList<VOLocation>();
            
            for(Integer id : cycle){
                node.neighbourLocations.add(map.GetLocationById(id));
            }
            
            for(Vec2d v : grinds){
                if(cycle.contains((int)v.x) && cycle.contains((int)v.y)){
                    int count = 0;
                    for(ArrayList<Integer> c : cycles){
                        if(c.contains((int)v.x) && c.contains((int)v.y)){
                            count++;
                        }
                    }
                    
                    if(count < 2){
                        node.toBorderCount++;
                    }
                }
            }
            
            ArrayList<LocationsNode> tempNodes = new ArrayList<LocationsNode>();
            
            for(LocationsNode n : nodeGraph){
                ArrayList<Integer> locIDs = new ArrayList<Integer>();
                for(VOLocation l : n.neighbourLocations){
                    if(node.neighbourLocations.contains(l))
                        locIDs.add(l.id);
                }
                if(locIDs.size() == 2){
                    n.neighbourNodes.add(node);
                    node.neighbourNodes.add(n);
                }
                else if(locIDs.size() > 2){
                    for(Vec2d v : grinds){
                        if(locIDs.contains((int)v.x) && locIDs.contains((int)v.y)){
                            LocationsNode nNode = new LocationsNode();
                            nNode.neighbourLocations = new ArrayList<VOLocation>();
                            nNode.neighbourNodes = new ArrayList<LocationsNode>();
                            nNode.toBorderCount = 0;
                            
                            nNode.neighbourLocations.add(map.GetLocationById((int)v.x));
                            nNode.neighbourLocations.add(map.GetLocationById((int)v.y));
                            
                            nNode.neighbourNodes.add(n);
                            nNode.neighbourNodes.add(node);
                            n.neighbourNodes.add(nNode);
                            node.neighbourNodes.add(nNode);
                            
                            tempNodes.add(nNode);
                        }
                    }
                }
            }
            
            nodeGraph.add(node);
            nodeGraph.addAll(tempNodes);
        }
        
        ArrayList<ArrayList<LocationsNode>> nodeCycles = new ArrayList<ArrayList<LocationsNode>>();
        
        ArrayList<LocationsNode> notCycledNodes = new ArrayList<LocationsNode>();
        
        for(LocationsNode n : nodeGraph){
            if(n.neighbourNodes.isEmpty()){
               notCycledNodes.add(n);
            }
            else{
                boolean assigned = false;
                for(ArrayList<LocationsNode> c : nodeCycles){
                    if(NodeNeigboursInArray(c, n, new ArrayList<LocationsNode>())){
                        assigned = true;
                        c.add(n);
                    }
                }
                if(!assigned){
                    ArrayList<LocationsNode> nodeCycle = new ArrayList<LocationsNode>();
                    nodeCycle.add(n);
                    nodeCycles.add(nodeCycle);
                }
            }
        }
        
        nodeCycles = SortNodeCycles(nodeCycles, notCycledNodes);
        notCycledNodes = SortNotCycledNode(nodeCycles, notCycledNodes);
        
        ArrayList<Vec2d> sides = new ArrayList<Vec2d>(nodeCycles.size());
        
        float totalSize = 0;
        
        for (ArrayList<LocationsNode> nodeCycle : nodeCycles) {
            totalSize += nodeCycle.size();
        }
        
        float prevSize = 0;
        
        for(int i = 0; i < nodeCycles.size(); i++){
            sides.set(i, new Vec2d(prevSize, prevSize + totalSize/nodeCycles.get(i).size()));
            prevSize += totalSize/nodeCycles.get(i).size();
        }
        
        for(int i = 0; i < nodeCycles.size(); i++){
            ArrayList<LocationsNode> borders = new ArrayList<LocationsNode>();
            for(LocationsNode loc : nodeCycles.get(i)){
                if(loc.toBorderCount > 0){
                    borders.add(loc);
                }
            }
            
            float horWidht = (float) (sides.get(i).y - sides.get(i).x);
            horWidht *= 0.4;
            
            float centerShift = (float) (sides.get(i).y - horWidht * 2.5 * 0.5);
            
            ArrayList<Vec2d> borderPoints = new ArrayList<Vec2d>();
            
            for(int j = 0; i < borders.size(); j++){
                borderPoints.add(new Vec2d(centerShift + Math.sin(Math.PI * ((float)j)/(float)borders.size()), 0.5 + Math.cos(Math.PI * ((float)j)/(float)borders.size())));
            }
            
            
        }
        
        
        
        
        
        for(VOLocation loc : map.locations){
            VOFieldLocation fLoc = new VOFieldLocation();
            fLoc.data = loc;
            fLoc.points = new ArrayList<Vec2d>();
            
            
        }
        
        
        return locations;
    }
    
    private static ArrayList<ArrayList<LocationsNode>> SortNodeCycles(ArrayList<ArrayList<LocationsNode>> nodeCycles, ArrayList<LocationsNode> notCycledNodes){
        if(notCycledNodes.isEmpty()) return nodeCycles;
        
        ArrayList<ArrayList<LocationsNode>> newArr = new ArrayList<ArrayList<LocationsNode>>(nodeCycles.size());
        ArrayList<LocationsNode> newNCArr = new ArrayList<LocationsNode>(notCycledNodes.size());
        
        for(ArrayList<LocationsNode> c : nodeCycles){
            int count = 0;
            LocationsNode neighNode = null;
            for(LocationsNode n : notCycledNodes){
                for(VOLocation loc : n.neighbourLocations){
                    if(NodeNeigbourLocInArray(c, loc)){
                        count++;
                        neighNode = n;
                    }
                }
            }
            
            if(count < 0){
               newArr.add(c);
               if(neighNode != null){
                   newNCArr.add(neighNode);
               }
               break;
            }
        }
        
        LocationsNode borderNode = newNCArr.get(0);
        
        for(int i = 1; i < newArr.size(); i++){
            for(ArrayList<LocationsNode> c : nodeCycles){
                for(VOLocation loc : borderNode.neighbourLocations){
                    if(NodeNeigbourLocInArray(c, loc) && !NodeNeigbourLocInArray(newArr.get(i - 1), loc)){
                        newArr.set(i, c);
                    }
                }
            }
            
            for(LocationsNode n : notCycledNodes){
                for(VOLocation loc : n.neighbourLocations){
                    if(NodeNeigbourLocInArray(newArr.get(i), loc) && !newNCArr.contains(n)){
                        newNCArr.set(i, n);
                    }
                }
            }
            
            if(i < newNCArr.size())
                borderNode = newNCArr.get(i);
            else
                borderNode = null;
        }
        
        return newArr;
    }
    
    private static ArrayList<LocationsNode> SortNotCycledNode(ArrayList<ArrayList<LocationsNode>> nodeCycles,ArrayList<LocationsNode> notCycledNodes){
        if(notCycledNodes.isEmpty()) return notCycledNodes;
        
        ArrayList<ArrayList<LocationsNode>> newArr = new ArrayList<ArrayList<LocationsNode>>(nodeCycles.size());
        ArrayList<LocationsNode> newNCArr = new ArrayList<LocationsNode>(notCycledNodes.size());
        
        for(ArrayList<LocationsNode> c : nodeCycles){
            int count = 0;
            LocationsNode neighNode = null;
            for(LocationsNode n : notCycledNodes){
                for(VOLocation loc : n.neighbourLocations){
                    if(NodeNeigbourLocInArray(c, loc)){
                        count++;
                        neighNode = n;
                    }
                }
            }
            
            if(count < 0){
               newArr.add(c);
               if(neighNode != null){
                   newNCArr.add(neighNode);
               }
               break;
            }
        }
        
        LocationsNode borderNode = newNCArr.get(0);
        
        for(int i = 1; i < newArr.size(); i++){
            for(ArrayList<LocationsNode> c : nodeCycles){
                for(VOLocation loc : borderNode.neighbourLocations){
                    if(NodeNeigbourLocInArray(c, loc) && !NodeNeigbourLocInArray(newArr.get(i - 1), loc)){
                        newArr.set(i, c);
                    }
                }
            }
            
            for(LocationsNode n : notCycledNodes){
                for(VOLocation loc : n.neighbourLocations){
                    if(NodeNeigbourLocInArray(newArr.get(i), loc) && !newNCArr.contains(n)){
                        newNCArr.set(i, n);
                    }
                }
            }
            
            if(i < newNCArr.size())
                borderNode = newNCArr.get(i);
            else
                borderNode = null;
        }
        
        return newNCArr;
    }
    
    private static boolean NodeNeigboursInArray(ArrayList<LocationsNode> list, LocationsNode node, ArrayList<LocationsNode> checked){
        
        if(list.contains(node))return true;
        
        for(LocationsNode neigh : node.neighbourNodes){
            if(list.contains(neigh)){
                return true;
            }
        }
        
        checked.add(node);
        
        for(LocationsNode neigh : node.neighbourNodes){
            if(!checked.contains(node)){
                if(NodeNeigboursInArray(list, neigh, checked)) return true;
            }
        }
        
        return false;
    }
    
    private static boolean NodeNeigbourLocInArray(ArrayList<LocationsNode> list, VOLocation loc){
        for(LocationsNode n : list){
            if(n.neighbourLocations.contains(loc)) return true;
        }
        
        return false;
    }
    
    private static List<ArrayList<Integer>> GetAllCycles(VOMap map){
        List<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>();
        List<ArrayList<Integer>> tempCycles = new ArrayList<ArrayList<Integer>>();
        
        for (VOLocation location : map.locations) {
            tempCycles.addAll(FindRecoursiveCycle(map, location.id, location.id, location.id));
        }
        
        for(int i = 0; i < tempCycles.size(); i++){
            boolean same = true;
            for(int j = 0; j < cycles.size(); j++){
                if(i != j){
                    for(Integer a : tempCycles.get(i)){
                        if(!cycles.get(j).contains(a)){
                            same = false;
                            break;
                        }
                    }
                }
            }
            if(!same){
                cycles.add(tempCycles.get(i));
            }
        }
        
        return cycles;
    }
    
    private static List<ArrayList<Integer>> FindRecoursiveCycle(VOMap map, int startInd, int curInd, int prevInd){
        List<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < map.locations.size(); i++){
            if(prevInd != map.locations.get(i).id && curInd != map.locations.get(i).id){
                for(int j = 0; j < map.locations.get(i).neighbours.size(); j++){
                    if(map.locations.get(i).neighbours.get(j).id == startInd){
                        cycles.add(new ArrayList<Integer>());
                        cycles.get(cycles.size() - 1).add(curInd);
                    }
                    else{
                        List<ArrayList<Integer>> endCycles = FindRecoursiveCycle(map, startInd, map.locations.get(i).neighbours.get(j).id, curInd);
                        for(int k = 0; k < endCycles.size(); k++){
                            if(!endCycles.get(k).contains(curInd)){
                                endCycles.get(k).add(curInd);
                                cycles.add(endCycles.get(k));
                            }
                        }
                    }
                }
            }
        }
        
        return cycles;
    }
}
