package com.byteowls.vaadin.medium.editor;

import com.byteowls.vaadin.medium.editor.option.Options;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

@StyleSheet({"vaadin://css/medium-editor.min.css", "vaadin://css/default.min.css" })
@JavaScript({ "vaadin://js/medium-editor.min.js", "vaadin://js/medium-editor-connector.js" })
public class MediumEditor extends AbstractJavaScriptComponentField<String> {
  
  public static final String CLASSNAME = "v-textarea";

  private static final long serialVersionUID = 7883922298355907015L;
  
  private boolean debugEnabled;
  private Options options;

  public MediumEditor() {}

  public MediumEditor(String caption) {
    this();
    setCaption(caption);
  }
  
  public void init() {
    addStyleName(CLASSNAME);
    // this function can be called in medium-editor-connector e.g. self.onValueChange(stringValue)
    addFunction("onValueChange", new JavaScriptFunction() {
      private static final long serialVersionUID = -4238188736600311222L;
      @Override
      public void call(JsonArray args) {
        String value = args.getString(0);
        if (value != null && (value.isEmpty() || value.equals("<p><br></p>"))) {
          value = null;
        }
        setValue(value);
      }
    });
  }

  public Options getOptions() {
    return options;
  }

  public void setOptions(Options options) {
    this.options = options;
  }
  
  public boolean isDebugEnabled() {
    return debugEnabled;
  }

  public void setDebugEnabled(boolean debugEnabled) {
    this.debugEnabled = debugEnabled;
  }

  @Override
  protected MediumEditorState getState() {
    return (MediumEditorState) super.getState();
  }

  @Override
  protected MediumEditorState getState(boolean markAsDirty) {
    return (MediumEditorState) super.getState(markAsDirty);
  }

  @Override
  public void attach() {
    init();
    getState().value = getValue();
    getState().options = getOptions();
    getState().debug = isDebugEnabled();
    super.attach();
  }

  @Override
  public boolean isEmpty() {
    return getValue() != null;
  }

  @Override
  public void clear() {
    setValue("");
    getState().value = getValue();
  }

  @Override
  public Class<? extends String> getType() {
    return String.class;
  }

}
