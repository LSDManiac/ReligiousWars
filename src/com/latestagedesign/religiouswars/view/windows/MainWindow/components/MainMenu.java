package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.view.gui.GraphicGap;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.HelpButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.QuickStartButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.ResumeButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JComponent{
    
    public static int PREFERRED_BUTTON_WIDTH = 120;
    public static int PREFERRED_BUTTON_HEIGHT = 40;
    public static int BUTTON_GAP = 10;
    
    private static MainMenu _instance;
    public static MainMenu getinstance(){
        if(_instance == null) _instance = new MainMenu();
        return _instance;
    }
    
    public MainMenu(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        Box j = new Box(BoxLayout.Y_AXIS);
        
        j.add(Box.createVerticalGlue());
        j.add(new ResumeButton());
        j.add(Box.createRigidArea(new Dimension(0, BUTTON_GAP)));
        j.add(new QuickStartButton());
        j.add(Box.createRigidArea(new Dimension(0, BUTTON_GAP)));
        j.add(new CustomStartButton());
        j.add(Box.createRigidArea(new Dimension(0, BUTTON_GAP)));
        j.add(new HelpButton());
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
