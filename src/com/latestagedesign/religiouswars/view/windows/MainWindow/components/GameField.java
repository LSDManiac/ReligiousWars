package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.exceptions.InitializationException;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.control.field.UnitController;
import com.latestagedesign.religiouswars.model.Constants;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import com.sun.javafx.geom.Vec2f;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.util.List;
import javax.swing.JComponent;

public class GameField extends JComponent{
    
    private FieldController controller;
    
    public List<VOFieldLocation> fieldLocations;
    
    private final MonuseListener ml = new MonuseListener();
    
    public GameField() throws InitializationException{
        controller = FieldController.getinstance();
        controller.Init(this);
        addMouseListener(ml);
        
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        DrawField(g);
    }
    
    public void DrawField(Graphics g){
        if(fieldLocations == null || fieldLocations.size() <= 0){
            //System.out.println("fieldLocations == null " + (fieldLocations == null));
            return;
        }
        
        Graphics2D g2 = (Graphics2D)g;
        
        for(VOFieldLocation l : fieldLocations){
            int xPoints[] = new int[l.data.borders.size()];
            int yPoints[] = new int[l.data.borders.size()];
            
            xPoints[0] = (int)(l.data.borders.get(0).getKey().x * (getWidth() - 1));
            yPoints[0] = (int)(l.data.borders.get(0).getKey().y * (getHeight() - 1));
            
            int prevIndex = 0;
            
            Vec2f prevP = new Vec2f(l.data.borders.get(0).getKey().x, l.data.borders.get(0).getKey().y);
            
            for(int i = 1; i < l.data.borders.size(); i++){
                boolean found = false;
                for(int j = 1; j < l.data.borders.size(); j++){
                    if(!found){
                        if(l.data.borders.get(j).getKey().x == prevP.x
                                && l.data.borders.get(j).getKey().y == prevP.y
                                && j != prevIndex){
                            prevP = l.data.borders.get(j).getValue();
                            prevIndex = j;
                            xPoints[i] = (int)(prevP.x * (getWidth() - 1));
                            yPoints[i] = (int)(prevP.y * (getHeight() - 1));
                            found = true;
                        }
                        else if(l.data.borders.get(j).getValue().x == prevP.x
                                && l.data.borders.get(j).getValue().y == prevP.y
                                && j != prevIndex){
                            prevP = l.data.borders.get(j).getKey();
                            prevIndex = j;
                            xPoints[i] = (int)(prevP.x * (getWidth() - 1));
                            yPoints[i] = (int)(prevP.y * (getHeight() - 1));
                            found = true;
                        }
                    }
                }
            }
            
            Polygon pol = new Polygon(xPoints, yPoints, yPoints.length);
            
            l.polygon = pol;
            
            int centerx = 0;
            int centery = 0;
            
            int minX = Integer.MAX_VALUE;
            
            for(int i = 0; i < xPoints.length; i++){
                centerx += xPoints[i];
                centery += yPoints[i];
                if(minX > xPoints[i])
                    minX = xPoints[i];
            }
            
            centerx /= xPoints.length;
            centery /= xPoints.length;
            
            int center = (centerx + minX) / 2;
            centerx = (centerx + center) / 2;
            
            l.center = new Point(centerx, centery);
            
            g2.setColor(l.curColor);
            g2.setStroke(new BasicStroke(1));
            g2.fillPolygon(pol);
        }
        
        boolean has1 = false;
        Color color1 = Color.black;
        int size1 = 1;
        GeneralPath pol1 = new GeneralPath();
        
        boolean has2 = false;
        Color color2 = Color.black;
        int size2 = 1;
        GeneralPath pol2 = new GeneralPath();
        
        for(VOFieldLocation l : fieldLocations){
            int xPts[] = l.polygon.xpoints;
            int yPts[] = l.polygon.ypoints;
            GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xPts.length);
            polygon.moveTo(xPts[0], yPts[0]);
            for (int index = 1; index < xPts.length; index++) {
                polygon.lineTo(xPts[index], yPts[index]);
            };
            
            polygon.closePath();
            
            if(controller.curState == FieldController.ControllerStates.BUILDING
                    && controller.selectedBuildLocation == l.data.id){
                has1 = true;
                color1 = Constants.BORDER_BUILDING_COLOR;
                size1 = Constants.BORDER_BUILDING_SIZE;
                pol1 = polygon;
            }
            else if(controller.curState == FieldController.ControllerStates.ATTACKING
                    && controller.selectedAttackingLocation == l.data.id){
                has1 = true;
                color1 = Constants.BORDER_ATTACKING_COLOR;
                size1 = Constants.BORDER_ATTACKING_SIZE;
                pol1 = polygon;
            }
            else if(controller.curState == FieldController.ControllerStates.ATTACKING
                    && controller.selectedAttackedLocation == l.data.id){
                has2 = true;
                color2 = Constants.BORDER_ATTACKED_COLOR;
                size2 = Constants.BORDER_ATTACKED_SIZE;
                pol2 = polygon;
            }
            else{
                g2.setColor(Constants.BORDER_UNSELECTED_COLOR);
                g2.setStroke(new BasicStroke(Constants.BORDER_UNSELECTED_SIZE));
            
                g2.draw(polygon);
            }
            //g2.setColor(Color.BLACK);
            //g2.drawString(l.data.id + ") " + l.unitsOnLocation.get(l.curOwnerId).get(UnitController.UnitType.PEASANT) + ", " + l.unitsOnLocation.get(l.curOwnerId).get(UnitController.UnitType.SOLDIER) + ", " + l.unitsOnLocation.get(l.curOwnerId).get(UnitController.UnitType.PRIEST), 5, 10 * l.data.id);
        }
        
        if(has2){
            g2.setColor(color2);
            g2.setStroke(new BasicStroke(size2));

            g2.draw(pol2);
        }
        
        if(has1){
            g2.setColor(color1);
            g2.setStroke(new BasicStroke(size1));

            g2.draw(pol1);
        }
        
        for(VOFieldLocation l : fieldLocations){
            PaintUnits(g2, l);
        }
        
        if(controller.curState == FieldController.ControllerStates.ATTACKING){
            int b = 30;
            int a = 20;
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.black);
            Point atCenter = controller.curMap.GetLocationById(controller.selectedAttackedLocation).fLoc.center; //2
            Point atkCenter = controller.curMap.GetLocationById(controller.selectedAttackingLocation).fLoc.center; //1
            
            g2.drawLine(atCenter.x, atCenter.y, atkCenter.x, atkCenter.y);
            
            double k1 = ((double)atCenter.y - (double)atkCenter.y) / ((double)atCenter.x - (double)atkCenter.x);
            double k2 = ((double)atkCenter.x - (double)atCenter.x) / ((double)atCenter.y - (double)atkCenter.y);
            double p1 = ((double)atkCenter.y * (double)atCenter.x - (double)atCenter.y * (double)atkCenter.x) / ((double)atCenter.x - (double)atkCenter.x);
            double A = 1 + k1 * k1;
            double B = 2 * k1 * p1 - 2 * (double)atCenter.x - 2 * k1 * (double)atCenter.y;
            double C = (double)atCenter.x * (double)atCenter.x + p1 * p1 + (double)atCenter.y * (double)atCenter.y - b * b - 2 * p1 * (double)atCenter.y;
            double x0 = (- B + (int)Math.sqrt(B * B - 4 * A * C)) / (2 * A);
            double y0 = k1 * x0 + p1;
            if(x0 < Math.min(atCenter.x, atkCenter.x)
                    || x0 > Math.max(atCenter.x, atkCenter.x)
                    || y0 < Math.min(atCenter.y, atkCenter.y)
                    || y0 > Math.max(atCenter.y, atkCenter.y)){
                x0 = (- B - (int)Math.sqrt(B * B - 4 * A * C)) / (2 * A);
                y0 = k1 * x0 + p1;
            }
            
            double p2 = y0 + x0 / k1;
            double A1 = 1 + k2 * k2;
            double B1 = 2 * k2 * p2 - 2 * k2 * y0 - 2 * x0;
            double C1 = p2 * p2 + y0 * y0 + x0 * x0 - 2 * p2 * y0 - a * a;
            
            double x31 = (-B1 + (int)Math.sqrt(B1 * B1 - 4 * A1 * C1)) / (2 * A1);
            double x32 = (-B1 - (int)Math.sqrt(B1 * B1 - 4 * A1 * C1)) / (2 * A1);
            
            double y31 = k2 * x31 + p2;
            double y32 = k2 * x32 + p2;
            
            System.out.println(x31 + " " + y31 + " | " + x32 + " " + y32);
            
            Point arrowLine1 = new Point((int)x31, (int)y31);
            Point arrowLine2 = new Point((int)x32, (int)y32);
            
            g2.drawLine(atCenter.x, atCenter.y, arrowLine1.x, arrowLine1.y);
            g2.drawLine(atCenter.x, atCenter.y, arrowLine2.x, arrowLine2.y);
        }
    }
    
    private static int PEASANT_X_SHIFT = 5;
    private static int PEASANT_Y_SHIFT = 5;
    
    private static int SOLDIER_X_SHIFT = 13;
    private static int SOLDIER_Y_SHIFT = 13;
    
    private static int PRIEST_X_SHIFT = 13;
    private static int PRIEST_Y_SHIFT = 13;
    
    private void PaintUnits(Graphics2D g2, VOFieldLocation l){
        boolean peasantTop = false;
        int peasantX = l.center.x - PEASANT_X_SHIFT;
        int peasantY = l.center.y;
        int soldierX = l.center.x;
        int soldierY = l.center.y - SOLDIER_Y_SHIFT;
        int priestX = l.center.x;
        int priestY = l.center.y + PRIEST_Y_SHIFT;
        for(int i : l.unitsOnLocation.keySet()){
            if(l.unitsOnLocation.get(i).containsKey(UnitController.UnitType.PEASANT)){
                for(int j = 0; j < l.unitsOnLocation.get(i).get(UnitController.UnitType.PEASANT); j++){
                    Point p = new Point(peasantX - PEASANT_X_SHIFT, peasantY);
                    if(HitPolygon(p, l.polygon, UnitController.UnitType.PEASANT)){
                        PaintUnitOnPos(g2, UnitController.UnitType.PEASANT, p, PlayersController.getinstance().GetPlayerColorById(i));
                    }
                    else{
                        p = new Point(l.center.x - 2 * PEASANT_X_SHIFT, peasantY - PEASANT_Y_SHIFT);
                        if(peasantY == l.center.y && HitPolygon(p, l.polygon, UnitController.UnitType.PEASANT)){
                            PaintUnitOnPos(g2, UnitController.UnitType.PEASANT, p, PlayersController.getinstance().GetPlayerColorById(i));
                            peasantTop = true;
                        }
                        else{
                            if(peasantTop){
                                p = new Point(l.center.x - 2 * PEASANT_X_SHIFT, -peasantY);
                                peasantTop = false;
                            }
                            else{
                                p = new Point(l.center.x - 2 * PEASANT_X_SHIFT, -peasantY - PEASANT_Y_SHIFT);
                                peasantTop = true;
                            }
                            if(HitPolygon(p, l.polygon, UnitController.UnitType.PEASANT)){
                                PaintUnitOnPos(g2, UnitController.UnitType.PEASANT, p, PlayersController.getinstance().GetPlayerColorById(i));
                            }
                            else{
                                p = new Point(peasantX, peasantY);
                            }
                        }
                    }
                    peasantX = p.x;
                    peasantY = p.y;
                }
            }
            
            if(l.unitsOnLocation.get(i).containsKey(UnitController.UnitType.SOLDIER)){
                for(int j = 0; j < l.unitsOnLocation.get(i).get(UnitController.UnitType.SOLDIER); j++){
                    Point p = new Point(soldierX + SOLDIER_X_SHIFT, soldierY);
                    if(HitPolygon(p, l.polygon, UnitController.UnitType.SOLDIER)){
                        PaintUnitOnPos(g2, UnitController.UnitType.SOLDIER, p, PlayersController.getinstance().GetPlayerColorById(i));
                    }
                    else{
                        p = new Point(l.center.x + SOLDIER_X_SHIFT, soldierY - SOLDIER_Y_SHIFT);
                        if(HitPolygon(p, l.polygon, UnitController.UnitType.SOLDIER)){
                            PaintUnitOnPos(g2, UnitController.UnitType.SOLDIER, p, PlayersController.getinstance().GetPlayerColorById(i));
                        }
                        else{
                            p = new Point(soldierX, soldierY);
                        }
                    }
                    soldierX = p.x;
                    soldierY = p.y;
                }
            }
            
            if(l.unitsOnLocation.get(i).containsKey(UnitController.UnitType.PRIEST)){
                for(int j = 0; j < l.unitsOnLocation.get(i).get(UnitController.UnitType.PRIEST); j++){
                    Point p = new Point(priestX + PRIEST_X_SHIFT, priestY);
                    if(HitPolygon(p, l.polygon, UnitController.UnitType.PRIEST)){
                        PaintUnitOnPos(g2, UnitController.UnitType.PRIEST, p, PlayersController.getinstance().GetPlayerColorById(i));
                    }
                    else{
                        p = new Point(l.center.x + PRIEST_X_SHIFT, priestY + PRIEST_Y_SHIFT);
                        if(HitPolygon(p, l.polygon, UnitController.UnitType.PRIEST)){
                            PaintUnitOnPos(g2, UnitController.UnitType.PRIEST, p, PlayersController.getinstance().GetPlayerColorById(i));
                        }
                        else{
                            p = new Point(priestX, priestY);
                        }
                    }
                    priestX = p.x;
                    priestY = p.y;
                }
            }
        }
    }
    
    private boolean HitPolygon(Point p, Polygon pl, UnitController.UnitType type){
        boolean hit = true;
        int shX = PEASANT_X_SHIFT / 2;
        int shY = PEASANT_Y_SHIFT / 2;
        if(type == UnitController.UnitType.PEASANT){
            shX = PEASANT_X_SHIFT / 2;
            shY = PEASANT_Y_SHIFT / 2;
        }
        if(type == UnitController.UnitType.PRIEST){
            shX = PRIEST_X_SHIFT / 2;
            shY = PRIEST_Y_SHIFT / 2;
        }
        if(type == UnitController.UnitType.SOLDIER){
            shX = SOLDIER_X_SHIFT / 2;
            shY = SOLDIER_Y_SHIFT / 2;
        }
        
        hit = hit && pl.contains(p);
        hit = hit && pl.contains(new Point(p.x + shX, p.y + shY));
        hit = hit && pl.contains(new Point(p.x + shX, p.y - shY));
        hit = hit && pl.contains(new Point(p.x - shX, p.y + shY));
        hit = hit && pl.contains(new Point(p.x - shX, p.y - shY));
        
        return hit;
    }
    
    private void PaintUnitOnPos(Graphics2D g2, UnitController.UnitType t, Point p, Color c){
        switch(t){
            case PEASANT:
                g2.setColor(c);
                g2.fillRect(p.x - 1, p.y - 1, 3, 3);
                break;
            case PRIEST:
                g2.setColor(Color.BLACK);
                g2.fillOval(p.x - 6, p.y - 6, 12, 12);
                g2.setColor(c);
                g2.fillOval(p.x - 5, p.y - 5, 10, 10);
                break;
            case SOLDIER:
                g2.setColor(Color.BLACK);
                g2.fillRect(p.x - 2, p.y - 5, 5, 11);
                g2.fillRect(p.x - 5, p.y - 2, 11, 5);
                g2.setColor(c);
                g2.fillRect(p.x - 1, p.y - 4, 3, 9);
                g2.fillRect(p.x - 4, p.y - 1, 9, 3);
                break;
        }
    }
    
    private int GetMouseDownLocationId(Point p){
        for(VOFieldLocation l : fieldLocations){
            if(l.polygon.contains(p)) return l.data.id;
        }
        
        return -1;
    }
    
    private final class MonuseListener extends MouseAdapter implements MouseMotionListener{
        private boolean mouseDown = false;
        private int mouseDownLocationId = -1;
        private int mouseUpLocationId = -1;

        @Override
        public void mouseExited(MouseEvent e) {
            mouseDown = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(mouseDown){
                mouseDown = false;
                return;
            }
            mouseDown = true;
            mouseDownLocationId = GetMouseDownLocationId(e.getPoint());
            //System.out.println("mouseDownLocationId = " + mouseDownLocationId);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(!mouseDown){
                return;
            }
            
            mouseDown = false;
            
            mouseUpLocationId = GetMouseDownLocationId(e.getPoint());
            //System.out.println("mouseUpLocationId = " + mouseUpLocationId);
            
            controller.SetLocationsSelected(mouseDownLocationId, mouseUpLocationId);
        }
    }
}
