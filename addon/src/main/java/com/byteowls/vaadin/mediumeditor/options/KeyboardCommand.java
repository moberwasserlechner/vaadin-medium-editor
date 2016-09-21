package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

public class KeyboardCommand implements Serializable {

    private static final long serialVersionUID = -7868932193555652991L;

    public String command;
    public String key;
    public Boolean meta;
    public Boolean shift;
    public Boolean alt;

}
