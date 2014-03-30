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

package com.latestagedesign.religiouswars.windows.MainWindow;

import com.latestagedesign.religiouswars.scripts.util.Localization;
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

    public MainWindow() {
        setDisplayName(Localization.Get("#religious_wars"));
    }
    
}
