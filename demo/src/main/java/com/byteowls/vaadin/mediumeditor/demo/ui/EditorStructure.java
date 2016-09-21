package com.byteowls.vaadin.mediumeditor.demo.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;

public enum EditorStructure {
    
    SIMPLE(FontAwesome.EDIT), 
    TRANSLATED(FontAwesome.GLOBE), 
    THEMED(FontAwesome.PAINT_BRUSH);
    
    FontIcon icon;
    
    private EditorStructure(FontIcon icon) {
        this.icon = icon;
    }

    public FontIcon getIcon() {
        return icon;
    }
    
}
