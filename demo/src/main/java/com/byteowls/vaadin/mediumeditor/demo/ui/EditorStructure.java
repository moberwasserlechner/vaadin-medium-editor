package com.byteowls.vaadin.mediumeditor.demo.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontIcon;

public enum EditorStructure {

    SIMPLE(VaadinIcons.EDIT), 
    TRANSLATED(VaadinIcons.GLOBE), 
    THEMED(VaadinIcons.PAINTBRUSH);

    FontIcon icon;

    private EditorStructure(FontIcon icon) {
        this.icon = icon;
    }

    public FontIcon getIcon() {
        return icon;
    }

}
