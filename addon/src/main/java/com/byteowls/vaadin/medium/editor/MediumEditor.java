package com.byteowls.vaadin.medium.editor;

import com.byteowls.vaadin.medium.editor.option.Options;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

@StyleSheet({"vaadin://css/medium-editor.min.css", "vaadin://css/bootstrap.min.css" })
@JavaScript({ "vaadin://js/medium-editor.min.js", "vaadin://js/medium-editor-connector.js" })
public class MediumEditor extends AbstractJavaScriptComponent {

  private static final long serialVersionUID = 7883922298355907015L;

  private String value;
  private Options options;

  public MediumEditor() {
    this(null);
  }

  public MediumEditor(Options options) {
    super();
    if (options == null) {
      this.options = new Options();
    } else {
      this.options = options;
    }

    // this function can be called in medium-editor-connector e.g. self.onValueChange(stringValue)
    addFunction("onValueChange", new JavaScriptFunction() {
      private static final long serialVersionUID = -4238188736600311222L;
      @Override
      public void call(JsonArray args) {
        value = args.getString(0);
      }
    });
  }

  public Options getOptions() {
    return options;
  }

  public void setOptions(Options options) {
    this.options = options;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
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
    getState().value = getValue();
    getState().options = getOptions();
    super.attach();
  }

}
