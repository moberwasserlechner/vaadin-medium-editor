package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class Placeholder implements Serializable {

    private static final long serialVersionUID = 7188275928268373605L;

    public String text;

    private Placeholder(PlaceholderBuilder builder) {
        text = builder.text;
    }

    public static PlaceholderBuilder builder(OptionsBuilder optionsBuilder) {
        return new PlaceholderBuilder(optionsBuilder);
    }

    public static class PlaceholderBuilder extends AbstractBuilder<Placeholder> {
        private OptionsBuilder optionsBuilder;

        private String text;
        private Boolean hideOnClick;

        public PlaceholderBuilder(OptionsBuilder optionsBuilder) {
            this.optionsBuilder = optionsBuilder;
        }

        /**
         * Defines the default placeholder for empty contenteditables when placeholder is not set to false. 
         * You can overwrite it by setting a data-placeholder attribute on the editor elements.
         * Default: 'Type your text'
         */
        public PlaceholderBuilder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Causes the placeholder to disappear as soon as the field gains focus. 
         * To hide the placeholder only after starting to type, and to show it again as soon as field is empty, set this option to false.
         * Default: true
         */
        public PlaceholderBuilder hideOnClick(boolean hideOnClick) {
            this.hideOnClick = hideOnClick;
            return this;
        }

        public OptionsBuilder done() {
            return optionsBuilder;
        }

        @Override
        public Placeholder build() {
            return new Placeholder(this);
        }

        @Override
        public JsonValue buildJson() {
            JsonObject map = Json.createObject();
            putNotNull(map, "text", text);
            putNotNull(map, "hideOnClick", hideOnClick);
            return map;
        }

    }

}
