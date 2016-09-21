package com.byteowls.vaadin.mediumeditor.options;

/**
 * Buildin editor buttons
 */
public enum Buttons {


    BOLD("bold"), ITALIC("italic"), UNDERLINE("underline"), 
    STRIKETHROUGH("strikethrough"), SUBSCRIPT("subscript"), SUPERSCRIPT("superscript"),
    FONTSIZE("fontsize"),
    ANCHOR("anchor"), QUOTE("quote"), PRE("pre"), ORDEREDLIST("orderedlist"), UNORDEREDLIST("unorderedlist"), INDENT("indent"), OUTDENT("outdent"),
    JUSTIFY_LEFT("justifyLeft"), JUSTIFY_CENTER("justifyCenter"), JUSTIFY_RIGHT("justifyRight"), JUSTIFY_FULL("justifyFull"), 
    H1("h1"), H2("h2"), H3("h3"), H4("h4"), H5("h5"), H6("h6"), REMOVE_FORMAT("removeFormat");

    private final String name;

    private Buttons(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
