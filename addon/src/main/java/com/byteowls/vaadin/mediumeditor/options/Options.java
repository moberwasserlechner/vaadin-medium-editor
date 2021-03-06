package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.byteowls.vaadin.mediumeditor.options.Anchor.AnchorBuilder;
import com.byteowls.vaadin.mediumeditor.options.AnchorPreview.AnchorPreviewBuilder;
import com.byteowls.vaadin.mediumeditor.options.KeyboardCommands.KeyboardCommandsBuilder;
import com.byteowls.vaadin.mediumeditor.options.PasteHandler.PasteHandlerBuilder;
import com.byteowls.vaadin.mediumeditor.options.Placeholder.PlaceholderBuilder;
import com.byteowls.vaadin.mediumeditor.options.Toolbar.ToolbarBuilder;
import com.vaadin.ui.UI;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;


public class Options implements Serializable {

    private static final long serialVersionUID = 3972297834616344210L;

    public String activeButtonClass;
    public Object buttonLabels;
    public Integer delay = null;
    public Boolean disableReturn;
    public Boolean disableDoubleReturn;
    public Boolean disableExtraSpaces;
    public Boolean disableEditing;
    // TODO selector which is resolved in javascript
    public String elementsContainerSelector;
    public Boolean spellcheck;
    public Boolean targetBlank;
    public Boolean autoLink;
    public Boolean imageDragging;

    public Toolbar toolbar;
    public AnchorPreview anchorPreview;
    public Placeholder placeholder;
    public Anchor anchor;
    public PasteHandler paste;
    public KeyboardCommands keyboardCommands;

    private Options(OptionsBuilder builder) {
        activeButtonClass = builder.activeButtonClass;
        if (builder.fontAwesomeLabels != null) {
            if (builder.fontAwesomeLabels) {
                buttonLabels = OptionsBuilder.BUTTON_LABEL_FONTAWESOME;
            } else {
                buttonLabels = Boolean.FALSE;
            }
        }
        delay = builder.delay;
        disableReturn = builder.disableReturn;
        disableDoubleReturn = builder.disableDoubleReturn;
        disableExtraSpaces = builder.disableExtraSpaces;
        disableEditing = builder.disableEditing;
        spellcheck = builder.spellcheck;
        targetBlank = builder.targetBlank;
        autoLink = builder.autoLink;
        imageDragging = builder.imageDragging;

        // toolbar
        if (builder.toolbarEnabled) {
            if (builder.toolbar != null) {
                toolbar = (Toolbar) builder.toolbar.build();
            }
        } else {
            //      toolbar = Boolean.FALSE;
        }

        // anchor preview
        if (builder.anchorPreviewEnabled) {
            if (builder.anchorPreview != null) {
                anchorPreview = builder.anchorPreview.build();
            }
        } else {
            //      anchorPreview = Boolean.FALSE;
        }

        // placeholder
        if (builder.placeholderEnabled) {
            if (builder.placeholder != null) {
                placeholder = builder.placeholder.build();
            }
        } else {
            //      placeholder = Boolean.FALSE;
        }

        // anchor
        if (builder.anchor != null) {
            anchor = builder.anchor.build();
        }

        // paste
        if (builder.pasteHandler != null) {
            paste = builder.pasteHandler.build();
        }

        // keyboard commands
        if (builder.keyboardCommandsEnabled) {
            if (builder.keyboardCommands != null) {
                keyboardCommands = builder.keyboardCommands.build();
            }
        } else {
            //      keyboardCommands = Boolean.FALSE;
        }

    }

    public static OptionsBuilder builder() {
        return new Options.OptionsBuilder();
    }

    public static class OptionsBuilder extends AbstractBuilder<Options> {

        private static final String BUTTON_LABEL_FONTAWESOME = "fontawesome";
        private static final String RESOURCE_BUNDLE_BASENAME = "com/byteowls/vaadin/mediumeditor/options/i18n";
        private String activeButtonClass;
        private Boolean fontAwesomeLabels = true;
        //    private Object buttonLabels = BUTTON_LABEL_FONTAWESOME;
        private Integer delay = null;
        private Boolean disableReturn;
        private Boolean disableDoubleReturn;
        private Boolean disableExtraSpaces;
        private Boolean disableEditing;
        private Boolean spellcheck;
        private Boolean targetBlank;
        private Boolean autoLink;
        private Boolean imageDragging;

        private boolean toolbarEnabled = true;
        private ToolbarBuilder toolbar;
        private boolean placeholderEnabled = true;
        private PlaceholderBuilder placeholder;

        private boolean anchorPreviewEnabled = true;
        private AnchorPreviewBuilder anchorPreview;

        private AnchorBuilder anchor;

        private PasteHandlerBuilder pasteHandler;
        private boolean keyboardCommandsEnabled = true;
        private KeyboardCommandsBuilder keyboardCommands;

        private boolean useDefaultLocaleFallback = false;
        private Locale locale;
        private ResourceBundle bundle;

        public OptionsBuilder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        /**
         * If true the ResourceBundle uses {@link Locale#getDefault()} as fallback locale, which might not English. 
         * Normally using this is not needed.
         * @param useDefaultLocaleFallback use the default-locale as fallback in the ResourceBundle
         * @return the {@link OptionsBuilder} for chaining.
         */
        public OptionsBuilder useDefaultLocaleFallback(boolean useDefaultLocaleFallback) {
            this.useDefaultLocaleFallback = useDefaultLocaleFallback;
            return this;
        }

        public String getTranslation(String code) {
            if (bundle == null) {
                Locale l = this.locale;
                if (l == null) {
                    if (UI.getCurrent() != null) {
                        l = UI.getCurrent().getLocale();
                    } else {
                        l = Locale.getDefault();
                    }
                }

                if (useDefaultLocaleFallback) {
                    bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASENAME, l);
                } else {
                    bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASENAME, l, Control.getNoFallbackControl(Control.FORMAT_PROPERTIES));
                }
            }
            return bundle.getString(code);
        }

        /**
         * CSS class added to active buttons in the toolbar.
         * Default: 'medium-editor-button-active'
         */
        public OptionsBuilder activeButtonClass(String activeButtonClass) {
            this.activeButtonClass = activeButtonClass;
            return this;
        }

        /**
         * Fontawesome buttons are use by default and must not be activated.
         */
        public OptionsBuilder fontawesomeButtonLabels() {
            this.fontAwesomeLabels = true;
            return this;
        }

        /**
         * Enables the default non font-awesome button labels.
         */
        public OptionsBuilder defaultButtonLabels() {
            this.fontAwesomeLabels = false;
            return this;
        }

        /**
         * Time in milliseconds to show the toolbar or anchor tag preview.
         * Default: 0
         */
        public OptionsBuilder delay(int delay) {
            this.delay = delay;
            return this;
        }

        /**
         * Enables/disables the use of the return-key. You can also set specific element behavior by using setting a data-disable-return attribute.
         * Default: false
         */
        public OptionsBuilder disableReturn(boolean disableReturn) {
            this.disableReturn = disableReturn;
            return this;
        }

        /**
         * Allows/disallows two (or more) empty new lines. You can also set specific element behavior by using setting a data-disable-double-return attribute.
         * Default: false
         */
        public OptionsBuilder disableDoubleReturn(boolean disableDoubleReturn) {
            this.disableDoubleReturn = disableDoubleReturn;
            return this;
        }

        /**
         * When set to true, it disallows spaces at the beginning and end of the element. Also it disallows entering 2 consecutive spaces between 2 words.
         * Default: false
         */
        public OptionsBuilder disableExtraSpaces(boolean disableExtraSpaces) {
            this.disableExtraSpaces = disableExtraSpaces;
            return this;
        }

        /**
         * Enables/disables adding the contenteditable behavior. Useful for using the toolbar with customized buttons/actions. You can also set specific element behavior by using setting a data-disable-editing attribute.
         * Default: false
         */
        public OptionsBuilder disableEditing(boolean disableEditing) {
            this.disableEditing = disableEditing;
            return this;
        }

        /**
         * Enable/disable native contentEditable automatic spellcheck. Default: true
         */
        public OptionsBuilder spellcheck(boolean spellcheck) {
            this.spellcheck = spellcheck;
            return this;
        }

        /**
         * Enables/disables automatically adding the target="_blank" attribute to anchor tags. Default: false
         */
        public OptionsBuilder targetBlank(boolean targetBlank) {
            this.targetBlank = targetBlank;
            return this;
        }

        /**
         * The auto-link handler is a built-in extension which automatically turns URLs entered into the text field into HTML anchor tags (similar to the functionality of Markdown). 
         * Default: false
         */
        public OptionsBuilder autoLink(boolean autoLink) {
            this.autoLink = autoLink;
            return this;
        }

        /**
         * The image dragging handler is a built-in extension for handling dragging & dropping images into the contenteditable. Default: true
         */
        public OptionsBuilder imageDragging(boolean imageDragging) {
            this.imageDragging = imageDragging;
            return this;
        }

        /**
         * Disables the toolbar.
         */
        public OptionsBuilder toolbarDisabled() {
            this.toolbarEnabled = false;
            return this;
        }

        /**
         * Step into toolbar configuration.
         */
        public ToolbarBuilder toolbar() {
            this.toolbarEnabled = true;
            if (toolbar == null) {
                toolbar = new ToolbarBuilder(this);
            }
            return toolbar;
        }

        /**
         * Disables the anchor preview.
         */
        public OptionsBuilder anchorPreviewDisabled() {
            this.anchorPreviewEnabled = false;
            return this;
        }

        /**
         * Step into anchor preview configuration.
         */
        public AnchorPreviewBuilder anchorPreview() {
            this.anchorPreviewEnabled = true;
            if (anchorPreview == null) {
                anchorPreview = new AnchorPreviewBuilder(this);
            }
            return anchorPreview;
        }

        /**
         * Disables the placeholders.
         */
        public OptionsBuilder placeholderDisabled() {
            this.placeholderEnabled = false;
            // mixing types does not work
            return placeholder().text("").done();
        }

        /**
         * Step into placeholder configuration.
         */
        public PlaceholderBuilder placeholder() {
            this.placeholderEnabled = true;
            if (placeholder == null) {
                placeholder = new PlaceholderBuilder(this);
            }
            return placeholder;
        }

        /**
         * Step into anchor configuration.
         */
        public AnchorBuilder anchor() {
            if (anchor == null) {
                anchor = new AnchorBuilder(this);
            }
            return anchor;
        }

        /**
         * Disables the paste handling
         */
        public OptionsBuilder pasteHandlingDisabled() {
            return paste().forcePlainText(false).cleanPastedHTML(false).done();
        }

        /**
         * Step into the paste option configuration
         */
        public PasteHandlerBuilder paste() {
            if (pasteHandler == null) {
                pasteHandler = new PasteHandlerBuilder(this);
            }
            return pasteHandler;
        }

        /**
         * Disables the keyboard commands.
         */
        public OptionsBuilder keyboardCommandsDisabled() {
            this.keyboardCommandsEnabled = false;
            return this;
        }

        /**
         * Step into keyboard command configuration.
         */
        public KeyboardCommandsBuilder keyboadCommands() {
            this.keyboardCommandsEnabled = true;
            if (keyboardCommands == null) {
                keyboardCommands = new KeyboardCommandsBuilder(this);
            }
            return keyboardCommands;
        }

        public OptionsBuilder done() {
            return this;
        }

        @Override
        public Options build() {
            return new Options(this);
        }

        @Override
        public JsonValue buildJson() {
            JsonObject map = Json.createObject();
            putNotNull(map, "activeButtonClass", activeButtonClass);
            if (fontAwesomeLabels != null) {
                if (fontAwesomeLabels) {
                    putNotNull(map, "buttonLabels", BUTTON_LABEL_FONTAWESOME);
                } else {
                    putNotNull(map, "buttonLabels", false);
                }
            }
            putNotNull(map, "delay", delay);
            putNotNull(map, "disableReturn", disableReturn);
            putNotNull(map, "disableDoubleReturn", disableDoubleReturn);
            putNotNull(map, "disableExtraSpaces", disableExtraSpaces);
            putNotNull(map, "disableEditing", disableEditing);
            putNotNull(map, "spellcheck", spellcheck);
            putNotNull(map, "targetBlank", targetBlank);
            putNotNull(map, "autoLink", autoLink);
            putNotNull(map, "imageDragging", imageDragging);

            // toolbar
            if (toolbarEnabled) {
                if (toolbar != null) {
                    putNotNull(map, "toolbar", toolbar.buildJson());
                }
            } else {
                putNotNull(map, "toolbar", Boolean.FALSE);
            }

            // anchor preview
            if (anchorPreviewEnabled) {
                if (anchorPreview != null) {
                    putNotNull(map, "anchorPreview", anchorPreview.buildJson());
                }
            } else {
                putNotNull(map, "anchorPreview", Boolean.FALSE);
            }

            // placeholder
            if (placeholderEnabled) {
                if (placeholder != null) {
                    putNotNull(map, "placeholder", placeholder.buildJson());
                }
            } else {
                putNotNull(map, "placeholder", Boolean.FALSE);
            }

            // anchor
            if (anchor != null) {
                putNotNull(map, "anchor", anchor.buildJson());
            }

            // paste
            if (pasteHandler != null) {
                putNotNull(map, "paste", pasteHandler.buildJson());
            }

            // keyboard commands
            if (keyboardCommandsEnabled) {
                if (keyboardCommands != null) {
                    putNotNull(map, "keyboardCommands", keyboardCommands.buildJson());
                }
            } else {
                putNotNull(map, "keyboardCommands", Boolean.FALSE);
            }
            return map;
        }
    }

}
