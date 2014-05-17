package com.latestagedesign.religiouswars.view.windows.MainWindow.components;

import javax.swing.JComponent;

public class HelpMenu extends JComponent{
    
    private static HelpMenu _instance;
    public static HelpMenu getinstance(){
        if(_instance == null) _instance = new HelpMenu();
        return _instance;
    }
    
}
