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

package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.control.exceptions.InitializationException;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.control.field.UnitController;
import com.latestagedesign.religiouswars.model.Constants;
import com.latestagedesign.religiouswars.model.VOClasses.VOFieldLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.sun.javafx.geom.Vec2f;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
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
            g2.setColor(Color.BLACK);
            g2.drawString(l.data.id + ") " + l.unitsOnLocation.get(l.curOwnerId).get(UnitController.UnitType.PEASANT) + ", " + l.unitsOnLocation.get(l.curOwnerId).get(UnitController.UnitType.SOLDIER) + ", " + l.unitsOnLocation.get(l.curOwnerId).get(UnitController.UnitType.PRIEST), 5, 10 * l.data.id);
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
