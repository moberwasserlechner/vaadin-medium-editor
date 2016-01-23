package com.byteowls.vaadin.mediumeditor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

@StyleSheet({"vaadin://mediumeditor/css/medium-editor.min.css", "vaadin://mediumeditor/css/default.min.css" })
@JavaScript({ "vaadin://mediumeditor/js/medium-editor.min.js", "vaadin://mediumeditor/js/medium-editor-connector.js" })
public class MediumEditor extends AbstractJavaScriptComponent {

  private static final long serialVersionUID = -5253978488866030676L;

  public interface ValueChangeListener extends Serializable {
    void valueChange(String value);
  }
  private List<MediumEditor.ValueChangeListener> valueChangeListeners = new ArrayList<MediumEditor.ValueChangeListener>();

  public MediumEditor() {
    init();
  }
  
  public void configure(Options.OptionsBuilder builder) {
    if (builder != null) {
      getState().options = builder.build();
    }
  }
  
  public static Options.OptionsBuilder options() {
    return Options.builder();
  }

  public void setContent(String content) {
    getState().content = content;
  }

  public void addValueChangeListener(MediumEditor.ValueChangeListener listener) {
    this.valueChangeListeners.add(listener);
  }

  public boolean isLoggingEnabled() {
    return getState().loggingEnabled;
  }

  public void setLoggingEnabled(boolean loggingEnabled) {
    getState().loggingEnabled = loggingEnabled;
  }

  public void setReadOnly(boolean readOnly) {
    getState().readOnly = readOnly;
  }

  public boolean isReadOnly() {
    return getState().readOnly;
  }
  
  public void setFocusOutlineEnabled(boolean focusOutlineEnabled) {
    getState().focusOutlineEnabled = focusOutlineEnabled;
  }
  
  public boolean isFocusOutlineEnabled() {
    return getState().focusOutlineEnabled;
  }

  @SuppressWarnings("serial")
  private void init() {
    setSizeFull();
    // this function can be called in medium-editor-connector e.g. self.onValueChange(stringValue)
    addFunction("onValueChange", new JavaScriptFunction() {
      @Override
      public void call(JsonArray args) {
        String value = args.getString(0);
        if (value != null && (value.isEmpty() || value.equals("<p><br></p>"))) {
          value = null;
        }
        for (ValueChangeListener l : valueChangeListeners) {
          l.valueChange(value);
        }
      }
    });
  }

  @Override
  public void attach() {
    super.attach();
  }

  @Override
  protected MediumEditorState getState() {
    return (MediumEditorState) super.getState();
  }

}
