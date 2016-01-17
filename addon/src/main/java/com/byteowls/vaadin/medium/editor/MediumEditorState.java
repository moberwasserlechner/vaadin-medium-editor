package com.byteowls.vaadin.medium.editor;

import com.byteowls.vaadin.medium.editor.option.Options;
import com.vaadin.shared.ui.JavaScriptComponentState;

public class MediumEditorState extends JavaScriptComponentState {

  private static final long serialVersionUID = -6062251484304417148L;

  public String value;
  public boolean loggingEnabled;
  public Options options = new Options();

}
