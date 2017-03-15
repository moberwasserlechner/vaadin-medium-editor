package com.byteowls.vaadin.mediumeditor;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

public class MediumEditorField extends CustomField<String> {

    private static final long serialVersionUID = -741003307371028467L;

    private MediumEditor editor;

    /**
     * Initialize a field without a caption.
     */
    public MediumEditorField() {
        super();
    }

    /**
     * Initialize a field with a caption.
     * @param caption the field's caption.
     */
    public MediumEditorField(String caption) {
        setCaption(caption);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        getEditor().setReadOnly(readOnly);
    }

    /**
     * @return the {@link MediumEditor} for configuring the options.
     */
    @SuppressWarnings("serial")
    public MediumEditor getEditor() {
        if (editor == null) {
            editor = new MediumEditor();
            // #7 control the border when a contenteditable gets the focus
            editor.setFocusOutlineEnabled(false);
            editor.addBlurListener(new MediumEditor.BlurListener() {
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
    public String getValue() {
        return getEditor().getState().content;
    }

    @Override
    protected void doSetValue(String value) {
        getEditor().setContent(value);
    }

}
