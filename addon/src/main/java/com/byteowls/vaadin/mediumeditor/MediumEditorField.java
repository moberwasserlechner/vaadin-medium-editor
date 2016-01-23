package com.byteowls.vaadin.mediumeditor;

import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

public class MediumEditorField extends CustomField<String> {

  private static final long serialVersionUID = -741003307371028467L;
  
  private MediumEditor editor;
  
  public MediumEditorField(String caption) {
    setCaption(caption);
  }
  
  @Override
  @SuppressWarnings("rawtypes")
  public void setPropertyDataSource(Property newDataSource) {
    super.setPropertyDataSource(newDataSource);
    getEditor().setContent(getValue());
  }
  
  public void setReadOnly(boolean readOnly) {
    super.setReadOnly(readOnly);
    getEditor().setReadOnly(readOnly);
  }
  
  @SuppressWarnings("serial")
  public MediumEditor getEditor() {
    if (editor == null) {
      editor = new MediumEditor();
      // #7 control the border when a contenteditable gets the focus
      editor.setFocusOutlineEnabled(false);
      editor.addValueChangeListener(new MediumEditor.ValueChangeListener() {
        @Override
        public void valueChange(String value) {
          setValue(value);
        }
      });
    }
    return editor;
  }

  @Override
  protected Component initContent() {
    addStyleName("v-textarea");
    return getEditor();
  }

  @Override
  public Class<? extends String> getType() {
    return String.class;
  }

}
