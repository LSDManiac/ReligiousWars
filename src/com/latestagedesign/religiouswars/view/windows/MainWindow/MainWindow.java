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

import com.latestagedesign.religiouswars.control.field.FieldController;
import com.latestagedesign.religiouswars.view.gui.UIImage;
import com.latestagedesign.religiouswars.model.Localization;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.BotBar;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.GameField;
import com.latestagedesign.religiouswars.view.windows.MainWindow.components.TopBar;
import java.awt.BorderLayout;
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
    
    private UIImage nataPic = new UIImage("images/nata.jpg");
    private javax.swing.JButton jButton1;
    
    private GameField field;
    
    public MainWindow() {
        setDisplayName(Localization.Get("#religious_wars"));
        
        this.setLayout(new BorderLayout());
        try{
            field = new GameField();
            add(new TopBar(), BorderLayout.NORTH);
            add(field, BorderLayout.CENTER);
            add(new BotBar(), BorderLayout.SOUTH);
            
            FieldController.getinstance().CreateField();
        }
        catch(Exception e){}
        //this.add(jButton1, BorderLayout.CENTER);
        
    }
    
}
