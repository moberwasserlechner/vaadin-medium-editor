package com.byteowls.vaadin.mediumeditor;

import com.byteowls.vaadin.mediumeditor.options.Options;
import com.byteowls.vaadin.mediumeditor.options.MediumEditorTheme;
import com.vaadin.shared.ui.JavaScriptComponentState;

import elemental.json.JsonValue;

public class MediumEditorState extends JavaScriptComponentState {

    private static final long serialVersionUID = -6062251484304417148L;

    public String content;
    public boolean loggingEnabled;
    public boolean focusOutlineEnabled = true;
    public Options options;
    public JsonValue optionsJson;
    public MediumEditorTheme theme = MediumEditorTheme.DEFAULT;
    public boolean readOnly;

}
