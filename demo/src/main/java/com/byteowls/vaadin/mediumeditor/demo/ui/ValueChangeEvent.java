package com.byteowls.vaadin.mediumeditor.demo.ui;

import java.io.Serializable;

public class ValueChangeEvent implements Serializable {
    
    private static final long serialVersionUID = -4156702536162775364L;
    
    private String editorId;
    private String value;
    
    public ValueChangeEvent(String value) {
        this.value = value;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueChangeEvent [editorId=" + editorId + ", value=" + value + "]";
    }
    
}
