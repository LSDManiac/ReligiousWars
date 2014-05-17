package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.QuickStartButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class CustomBattleMenu extends JComponent {
    
    private static int GAP = 10;
    private static int MAP_SIZE = 200;
    
    private static CustomBattleMenu _instance;
    public static CustomBattleMenu getinstance(){
        if(_instance == null) _instance = new CustomBattleMenu();
        return _instance;
    }
    
    public CustomBattleMenu(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        Box j = new Box(BoxLayout.Y_AXIS);
        
        j.add(Box.createVerticalGlue());
        j.add(Box.createRigidArea(new Dimension(0, MAP_SIZE)));
        j.add(Box.createRigidArea(new Dimension(0, GAP)));
        j.add(new QuickStartButton());
        j.add(Box.createVerticalGlue());
        
        this.add(Box.createHorizontalGlue());
        this.add(j);
        this.add(Box.createHorizontalGlue());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paint(g);
    }
}
