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

package com.latestagedesign.religiouswars.view.windows.MainWindow;

import com.latestagedesign.religiouswars.control.PlayersController;
import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.model.VOClasses.VOMap;
import com.latestagedesign.religiouswars.view.gui.UIImage;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.CustomBattleMenu;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.GameField;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.HelpMenu;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.MainMenu;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBar;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.windows.TopComponent;

@TopComponent.Description(preferredID = "MainWindow")
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "com.latestagedesign.religiouswars.windows.MainWindow")
@ActionReferences({
    @ActionReference(path = "Menu/Window")
})
@TopComponent.OpenActionRegistration(displayName = "Religious Wars", preferredID = "MainWindow")
public class MainWindow extends TopComponent{
    
    public enum MainWindowState{
        MAIN_MENU,
        HELP_MENU,
        CUSTOM_BATTLE_MENU,
        GAME
    }
    
    private static MainWindow _instance;
    public static MainWindow getinstance(){return _instance;}
    
    private MainWindowState curState;
    
    private GameField field;
    
    private JPanel gameScreen;
    private MainMenu mainMenu;
    private HelpMenu helpMenu;
    private CustomBattleMenu cbMenu;
    
    public MainWindow() {
        _instance = this;
        setDisplayName(Localization.Get("#religious_wars"));
        
        gameScreen = new JPanel();
        
        gameScreen.setLayout(new BoxLayout(gameScreen, BoxLayout.Y_AXIS));
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        try{
            field = new GameField();
            gameScreen.add(TopBar.getinstance());
            gameScreen.add(field);
            gameScreen.add(BotBar.getinstance());
        }
        catch(Exception e){}
        
        mainMenu = MainMenu.getinstance();
        helpMenu = HelpMenu.getinstance();
        cbMenu = CustomBattleMenu.getinstance();
        
        this.add(gameScreen);
        this.add(mainMenu);
        this.add(helpMenu);
        this.add(cbMenu);
        
        SwitchToState(MainWindowState.MAIN_MENU);
    }
    
    public void SwitchToState(MainWindowState state){
        curState = state;
        
        gameScreen.setVisible(false);
        mainMenu.setVisible(false);
        helpMenu.setVisible(false);
        cbMenu.setVisible(false);
        
        switch(state){
            case MAIN_MENU:
                mainMenu.setVisible(true);
                break;
            case CUSTOM_BATTLE_MENU:
                cbMenu.setVisible(true);
                break;
            case GAME:
                gameScreen.setVisible(true);
                break;
            case HELP_MENU:
                helpMenu.setVisible(true);
                break;
        }
    }
    
}
