package com.byteowls.vaadin.mediumeditor.demo.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface AddonView extends View {

    void setEditorReadOnly(boolean readonly);

    void setEditorText(String text);

    Component getAddonComponent();

    String getSource();

    String getViewName();

}
