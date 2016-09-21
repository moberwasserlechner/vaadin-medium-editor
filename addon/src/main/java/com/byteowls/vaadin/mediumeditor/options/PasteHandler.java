package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class PasteHandler implements Serializable {

    private static final long serialVersionUID = 5903047095226719103L;

    public Boolean forcePlainText;
    public Boolean cleanPastedHTML;
    // TODO cleanReplacements javascript regex function
    public List<String> cleanAttrs;
    public List<String> cleanTags;

    private PasteHandler(PasteHandlerBuilder builder) {
        forcePlainText = builder.forcePlainText;
        cleanPastedHTML = builder.cleanPastedHTML;
        cleanAttrs = builder.cleanAttrs;
        cleanTags = builder.cleanTags;
    }

    public static PasteHandlerBuilder builder(OptionsBuilder optionsBuilder) {
        return new PasteHandlerBuilder(optionsBuilder);
    }

    public static class PasteHandlerBuilder extends AbstractBuilder<PasteHandler> {
        // reference to parent
        private OptionsBuilder optionsBuilder;

        private Boolean forcePlainText;
        private Boolean cleanPastedHTML;
        private List<String> cleanAttrs;
        private List<String> cleanTags;
        private List<String> unwrapTags;

        public PasteHandlerBuilder(OptionsBuilder optionsBuilder) {
            this.optionsBuilder = optionsBuilder;
        }

        /**
         * Forces pasting as plain text. Default: true
         */
        public PasteHandlerBuilder forcePlainText(boolean forcePlainText) {
            this.forcePlainText = forcePlainText;
            return this;
        }

        /**
         * Cleans pasted content from different sources, like google docs etc. Default: false
         */
        public PasteHandlerBuilder cleanPastedHTML(boolean cleanPastedHTML) {
            this.cleanPastedHTML = cleanPastedHTML;
            return this;
        }

        /**
         * List of element attributes to remove during paste when cleanPastedHTML is true. Default: ['class', 'style', 'dir']
         */
        public PasteHandlerBuilder cleanAttrs(String... cleanAttrs) {
            if (this.cleanAttrs == null) {
                this.cleanAttrs = new ArrayList<>();
            }
            for (String a : cleanAttrs) {
                this.cleanAttrs.add(a);
            }
            return this;
        }

        /**
         * List of element tag names to remove during paste when cleanPastedHTML is true. Default: ['meta']
         */
        public PasteHandlerBuilder cleanTags(String... cleanTags) {
            if (this.cleanTags == null) {
                this.cleanTags = new ArrayList<>();
            }
            for (String t : cleanTags) {
                this.cleanTags.add(t);
            }
            return this;
        }

        /**
         * List of element tag names to unwrap (remove the element tag but retain its child elements) during paste when cleanPastedHTML is true. Default: []
         */
        public PasteHandlerBuilder unwrapTags(String... unwrapTags) {
            if (this.unwrapTags == null) {
                this.unwrapTags = new ArrayList<>();
            }
            for (String t : unwrapTags) {
                this.unwrapTags.add(t);
            }
            return this;
        }

        public OptionsBuilder done() {
            return optionsBuilder;
        }

        public PasteHandler build() {
            return new PasteHandler(this);
        }

        @Override
        public JsonValue buildJson() {
            JsonObject map = Json.createObject();
            putNotNull(map, "forcePlainText", forcePlainText);
            putNotNull(map, "cleanPastedHTML", cleanPastedHTML);
            putNotNull(map, "cleanAttrs", cleanAttrs);
            putNotNull(map, "cleanTags", cleanTags);
            putNotNull(map, "unwrapTags", unwrapTags);
            return map;
        }

    }

}
