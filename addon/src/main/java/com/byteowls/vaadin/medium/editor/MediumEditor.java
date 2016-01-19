package com.byteowls.vaadin.medium.editor;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

@StyleSheet({"vaadin://css/medium-editor.min.css", "vaadin://css/default.min.css" })
@JavaScript({ "vaadin://js/medium-editor.min.js", "vaadin://js/medium-editor-connector.js" })
public class MediumEditor extends AbstractJavaScriptComponent {
  
  private static final long serialVersionUID = -3726576588002521717L;

  public interface ValueChangeListener {
    void valueChange(String value);
  }
  private List<ValueChangeListener> valueChangeListeners = new ArrayList<MediumEditor.ValueChangeListener>();

  public MediumEditor() {
    init();
  }
  
  public void setContent(String content) {
    getState().content = content;
  }
  
  public void addValueChangeListener(ValueChangeListener listener) {
    this.valueChangeListeners.add(listener);
  }

  public boolean isLoggingEnabled() {
    return getState().loggingEnabled;
  }

  public void setLoggingEnabled(boolean loggingEnabled) {
    getState().loggingEnabled = loggingEnabled;
  }
  
  private void init() {
    setSizeFull();
    // this function can be called in medium-editor-connector e.g. self.onValueChange(stringValue)
    addFunction("onValueChange", new JavaScriptFunction() {
      private static final long serialVersionUID = -4238188736600311222L;
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
