package com.byteowls.vaadin.medium.editor;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

public class MediumEditorField extends CustomField<String> {

  private static final long serialVersionUID = -741003307371028467L;
  
  private MediumEditor editor;
  private boolean valueReceived = false; 
  
  @SuppressWarnings("serial")
  public MediumEditorField(String caption) {
    setCaption(caption);
    
    addValueChangeListener(new ValueChangeListener() {
      @Override
      public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
        if (!valueReceived) {
          getEditor().setContent(getValue());
        } else {
          valueReceived = false;
        }
      }
    });
  }
  
  public MediumEditor getEditor() {
    if (editor == null) {
      editor = new MediumEditor();
      editor.addValueChangeListener(new MediumEditor.ValueChangeListener() {
        @Override
        public void valueChange(String value) {
          valueReceived = true;
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
