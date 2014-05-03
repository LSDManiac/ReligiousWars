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
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.GameField;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBar;
import javax.swing.BoxLayout;
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
    
    private static MainWindow _instance;
    public static MainWindow getinstance(){return _instance;}
    
    private GameField field;
    
    public MainWindow() {
        _instance = this;
        setDisplayName(Localization.Get("#religious_wars"));
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try{
            field = new GameField();
            add(TopBar.getinstance());
            add(field);
            add(BotBar.getinstance());
            
            int playersNum = 3;
            
            PlayersController.getinstance().CreatePlayers(playersNum);
            FieldController.getinstance().CreateField(playersNum, VOMap.MapSize.USA);
            
        }
        catch(Exception e){}
        
    }
    
}
