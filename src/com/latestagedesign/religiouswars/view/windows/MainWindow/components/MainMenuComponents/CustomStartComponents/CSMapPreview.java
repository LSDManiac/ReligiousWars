package com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartComponents;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.model.Constants;
import com.latestagedesign.religiouswars.model.VOClasses.VOLocation;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.sun.javafx.geom.Vec2f;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import javax.swing.JComponent;

public class CSMapPreview extends JComponent {
    
    public static int MAP_WIDTH = 300;
    public static int MAP_HEIGHT = 200;
    
    public VOMap map;
    public int playerNum;
    
    public CSMapPreview(){
        this.setMinimumSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
        this.setMaximumSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
        this.setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
    }
    
    public void SetMap(VOMap _map, int _playerNum){
        this.map = _map;
        playerNum = _playerNum;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        if(map == null) return;
        
        Graphics2D g2 = (Graphics2D)g;
        
        int pl = 0;
        
        for(VOLocation l : map.locations){
            int xPoints[] = new int[l.borders.size()];
            int yPoints[] = new int[l.borders.size()];
            
            xPoints[0] = (int)(l.borders.get(0).getKey().x * (getWidth() - 1));
            yPoints[0] = (int)(l.borders.get(0).getKey().y * (getHeight() - 1));
            
            int prevIndex = 0;
            
            Vec2f prevP = new Vec2f(l.borders.get(0).getKey().x, l.borders.get(0).getKey().y);
            
            for(int i = 1; i < l.borders.size(); i++){
                boolean found = false;
                for(int j = 1; j < l.borders.size(); j++){
                    if(!found){
                        if(l.borders.get(j).getKey().x == prevP.x
                                && l.borders.get(j).getKey().y == prevP.y
                                && j != prevIndex){
                            prevP = l.borders.get(j).getValue();
                            prevIndex = j;
                            xPoints[i] = (int)(prevP.x * (getWidth() - 1));
                            yPoints[i] = (int)(prevP.y * (getHeight() - 1));
                            found = true;
                        }
                        else if(l.borders.get(j).getValue().x == prevP.x
                                && l.borders.get(j).getValue().y == prevP.y
                                && j != prevIndex){
                            prevP = l.borders.get(j).getKey();
                            prevIndex = j;
                            xPoints[i] = (int)(prevP.x * (getWidth() - 1));
                            yPoints[i] = (int)(prevP.y * (getHeight() - 1));
                            found = true;
                        }
                    }
                }
            }
            
            Polygon pol = new Polygon(xPoints, yPoints, yPoints.length);
            
            Color c = Constants.NOBODY_PROVINCE_COLOR;
            
            if(l.isStart && pl < playerNum){
                c = PlayersController.getinstance().GetTerritoryNumeredColor(pl);
                pl++;
            }
            
            g2.setColor(c);
            g2.setStroke(new BasicStroke(1));
            g2.fillPolygon(pol);
            
            int xPts[] = pol.xpoints;
            int yPts[] = pol.ypoints;
            GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xPts.length);
            polygon.moveTo(xPts[0], yPts[0]);
            for (int index = 1; index < xPts.length; index++) {
                polygon.lineTo(xPts[index], yPts[index]);
            };
            
            polygon.closePath();
            
            g2.setColor(Constants.BORDER_UNSELECTED_COLOR);
            g2.setStroke(new BasicStroke(Constants.BORDER_UNSELECTED_SIZE));

            g2.draw(polygon);
        }
    }
}
