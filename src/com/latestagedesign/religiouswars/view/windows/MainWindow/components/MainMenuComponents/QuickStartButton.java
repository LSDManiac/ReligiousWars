package com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.latestagedesign.religiouswars.view.gui.GraphicButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class QuickStartButton extends GraphicButton{
    
    private static int QUICK_START_PLAYER_NUM = 3;
    private static VOMap.MapSize QUICK_START_MAP = VOMap.MapSize.USA;
    
    JLabel label;
    
    public QuickStartButton(){
        this.setMinimumSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        this.setMaximumSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        this.setPreferredSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        addMouseListener(this);
        /*
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        
        label = new JLabel(Localization.Get("#quick_start"), JLabel.RIGHT);
        this.add(label);
        label.setSize(new Dimension(MainMenu.PREFERRED_BUTTON_WIDTH, MainMenu.PREFERRED_BUTTON_HEIGHT));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setAlignmentY(JLabel.CENTER_ALIGNMENT);*/
    }
    
    @Override
    public void paintContent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.BOLD, 14));
        String str = Localization.Get("#quick_start");
        int shift = (int)(str.length() / 2.0 * 8);
        g.drawString(str, getWidth()/2 - shift + x_shift, getHeight()/2 + 6 + y_shift);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PlayersController.getinstance().CreatePlayers(QUICK_START_PLAYER_NUM);
        FieldController.getinstance().CreateField(QUICK_START_PLAYER_NUM, QUICK_START_MAP);
        MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.GAME);
    }
    
}
