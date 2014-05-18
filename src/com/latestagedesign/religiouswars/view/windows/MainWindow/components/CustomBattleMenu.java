package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.control.field.FieldCreator;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.latestagedesign.religiouswars.view.gui.GraphicLabel;
import com.latestagedesign.religiouswars.view.gui.RadioButtonController;
import com.latestagedesign.religiouswars.view.windows.MainWindow.MainWindow;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartComponents.CSMapPreview;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartComponents.CSPlayerRadioButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartComponents.CSSizeRadioButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.CustomStartComponents.CSStartButton;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenuComponents.QuickStartButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class CustomBattleMenu extends JComponent {
    
    private static int GAP = 10;
    private static int LABEL_GAP = 30;
    private static int MAP_SIZE = 200;
    
    private static CustomBattleMenu _instance;
    public static CustomBattleMenu getinstance(){
        if(_instance == null) _instance = new CustomBattleMenu();
        return _instance;
    }
    
    private RadioButtonController sizeController;
    private RadioButtonController playerController;
    private CSMapPreview mapPreview;
    
    public CustomBattleMenu(){
        
        sizeController = new RadioButtonController();
        playerController = new RadioButtonController();
        mapPreview = new CSMapPreview();
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        Box k = new Box(BoxLayout.X_AXIS);
        
        for(int i = 0; i < 3; i++){
            CSSizeRadioButton r = new CSSizeRadioButton(i);
            r.size = GetSizeById(i);
            sizeController.AddRadioButton(r);
            k.add(r);
            if(i < 2){
                k.add(Box.createRigidArea(new Dimension(GAP, 0)));
            }
        }
        
        Box l = new Box(BoxLayout.X_AXIS);
        
        for(int i = 2; i < 5; i++){
            CSPlayerRadioButton r = new CSPlayerRadioButton(i);
            r.playerNum = i;
            playerController.AddRadioButton(r);
            l.add(r);
            if(i < 3){
                l.add(Box.createRigidArea(new Dimension(GAP, 0)));
            }
        }
        
        Box j = new Box(BoxLayout.Y_AXIS);
        j.add(Box.createVerticalGlue());
        j.add(mapPreview);
        j.add(Box.createRigidArea(new Dimension(0, GAP)));
        j.add(new GraphicLabel(Localization.Get("#map_size")));
        j.add(Box.createRigidArea(new Dimension(0, GAP)));
        j.add(k);
        j.add(Box.createRigidArea(new Dimension(0, GAP)));
        j.add(new GraphicLabel(Localization.Get("#players_num")));
        j.add(Box.createRigidArea(new Dimension(0, GAP)));
        j.add(l);
        j.add(Box.createRigidArea(new Dimension(0, GAP)));
        j.add(new CSStartButton());
        j.add(Box.createVerticalGlue());
        
        this.add(Box.createHorizontalGlue());
        this.add(j);
        this.add(Box.createHorizontalGlue());
    }
    
    public void ParametersChanged(){
        FieldCreator.CreatePreviewMap(playerController.GetChosenId(), GetSizeById(sizeController.GetChosenId()));
    }
    
    public void RecieveMapLoaded(VOMap map){
        mapPreview.SetMap(map, playerController.GetChosenId());
    }
    
    public void FireStartClicked(){
        
        PlayersController.getinstance().CreatePlayers(playerController.GetChosenId());
        FieldController.getinstance().CreateField(playerController.GetChosenId(), GetSizeById(sizeController.GetChosenId()));
        MainWindow.getinstance().SwitchToState(MainWindow.MainWindowState.GAME);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paint(g);
    }
    
    private static VOMap.MapSize GetSizeById(int id){
        VOMap.MapSize size = VOMap.MapSize.GERMANY;
        switch(id){
            case 0: size = VOMap.MapSize.UKRAINE; break;
            case 1: size = VOMap.MapSize.GERMANY; break;
            case 2: size = VOMap.MapSize.USA; break;
        }
        return size;
    }
}
