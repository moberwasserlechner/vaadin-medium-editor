package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;
import com.byteowls.vaadin.mediumeditor.options.ToolbarButton.ToolbarButtonBuilder;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class Toolbar implements Serializable {

    private static final long serialVersionUID = -3318254088223351177L;

    public Boolean allowMultiParagraphSelection;
    public List<ToolbarButton> buttons = new ArrayList<>();
    public Integer diffLeft = null;
    public Integer diffTop = null;
    public String firstButtonClass;
    public String lastButtonClass;
    public Boolean standardizeSelectionStart;
    // TODO this option is named "static" but obviously this is not possible.
    // TODO maybe there is a annotation setting another name
    public Boolean staticToolbar;
    public String align;
    public Boolean sticky;
    public Boolean updateOnEmptySelection;

    private Toolbar(ToolbarBuilder builder) {
        allowMultiParagraphSelection = builder.allowMultiParagraphSelection;
        if (builder.buttons != null) {
            for (ToolbarButtonBuilder tbb : builder.buttons) {
                buttons.add(tbb.build());
            }
        }
        diffLeft = builder.diffLeft;
        diffTop = builder.diffTop;
        firstButtonClass = builder.firstButtonClass;
        lastButtonClass = builder.lastButtonClass;
        standardizeSelectionStart = builder.standardizeSelectionStart;
        staticToolbar = builder.staticToolbar;
        align = builder.align;
        sticky = builder.sticky;
        updateOnEmptySelection = builder.updateOnEmptySelection;

    }

    public static ToolbarBuilder builder(OptionsBuilder optionsBuilder) {
        return new ToolbarBuilder(optionsBuilder);
    }

    public static class ToolbarBuilder extends AbstractBuilder<Toolbar> {
        private OptionsBuilder optionsBuilder;

        private Boolean allowMultiParagraphSelection;
        private List<ToolbarButtonBuilder> buttons = new ArrayList<>();
        private Integer diffLeft = null;
        private Integer diffTop = null;
        private String firstButtonClass;
        private String lastButtonClass;
        private Boolean standardizeSelectionStart;
        private Boolean staticToolbar;
        private String align;
        private Boolean sticky;
        private Boolean stickyTopOffset;
        private Boolean updateOnEmptySelection;

        public ToolbarBuilder(OptionsBuilder optionsBuilder) {
            this.optionsBuilder = optionsBuilder;
        }

        OptionsBuilder getOptionsBuilder() {
            return this.optionsBuilder;
        }

        /**
         * Enables/disables whether the toolbar should be displayed when selecting multiple paragraphs/block elements.
         * Default: true
         */
        public ToolbarBuilder allowMultiParagraphSelection(boolean allowMultiParagraphSelection) {
            this.allowMultiParagraphSelection = allowMultiParagraphSelection;
            return this;
        }

        /**
         * Enable the default buttons, which are ['bold', 'italic', 'underline', 'anchor', 'h2', 'h3', 'quote']
         * 
         */
        public ToolbarBuilder defaultButtons() {
            buttons(Buttons.BOLD, Buttons.ITALIC, Buttons.UNDERLINE, Buttons.ANCHOR, Buttons.H2, Buttons.H3, Buttons.QUOTE);
            return this;
        }

        /**
         * Enable all buttons.
         */
        public ToolbarBuilder allButtons() {
            for (Buttons b : Buttons.values()) {
                button(b);
            }
            return this;
        }

        /**
         * Set the translations to the existing buttons. The order is important.
         * @param translations the already added button's translations 
         * @return the {@link ToolbarBuilder} for chaining
         */
        public ToolbarBuilder buttonTranslations(String... translations) {
            if (translations != null) {
                int cnt = 0;
                int len = translations.length;

                for (ToolbarButtonBuilder b : buttons) {
                    if (cnt < len) {
                        b.aria(translations[cnt], true);
                    }
                    cnt++;
                }
            }
            return this;
        }

        /**
         * Add a build in button
         */
        public ToolbarBuilder button(Buttons button) {
            return button(button, null);
        }

        /**
         * Add a button with a tooltip
         */
        public ToolbarBuilder button(Buttons button, String tooltip) {
            ToolbarButtonBuilder tb = getExistingTb(button);
            if (tb == null) {
                tb = ToolbarButtonBuilder.getBuildin(button);
                tb.parentBuilder(this);
                buttons.add(tb);
            }
            if (tooltip != null) {
                tb.aria(tooltip, true);
            }
            return this;
        }

        /**
         * Add buttons
         */
        public ToolbarBuilder buttons(Buttons... buttons) {
            if (buttons != null) {
                if (this.buttons == null) {
                    this.buttons = new ArrayList<>();
                }
                for (Buttons b : buttons) {
                    button(b);
                }
            }
            return this;
        }

        /**
         * Add buttons as {@link ToolbarButtonBuilder}
         */
        public ToolbarBuilder buttons(ToolbarButtonBuilder... toolbarButtonBuilders) {
            if (toolbarButtonBuilders != null) {
                if (this.buttons == null) {
                    this.buttons = new ArrayList<>();
                }
                for (ToolbarButtonBuilder tbb : toolbarButtonBuilders) {
                    this.buttons.add(tbb);
                }
            }
            return this;
        }

        /**
         * Add a button before another one.
         */
        public ToolbarBuilder buttonBefore(Buttons before, Buttons incoming) {
            if (incoming != null) {
                if (before == null) {
                    buttons(incoming);
                } else {
                    ToolbarButtonBuilder beforeTb = ToolbarButtonBuilder.getBuildin(before);
                    beforeTb.parentBuilder(this);
                    ToolbarButtonBuilder incomingTb = ToolbarButtonBuilder.getBuildin(incoming);
                    incomingTb.parentBuilder(this);

                    int beforeIdx = buttons.indexOf(beforeTb);
                    int insertIdx = --beforeIdx;
                    if (insertIdx < 0) {
                        insertIdx = 0;
                    }
                    buttons.add(insertIdx, incomingTb);
                }
            }
            return this;
        }

        /**
         * Add a button after another one.
         */
        public ToolbarBuilder buttonAfter(Buttons after, Buttons incoming) {
            if (incoming != null) {
                if (after == null) {
                    buttons(incoming);
                } else {
                    ToolbarButtonBuilder afterTb = ToolbarButtonBuilder.getBuildin(after);
                    afterTb.parentBuilder(this);
                    ToolbarButtonBuilder incomingTb = ToolbarButtonBuilder.getBuildin(incoming);
                    incomingTb.parentBuilder(this);

                    int afterIdx = buttons.indexOf(afterTb);
                    int insertIdx = afterIdx++;
                    if (insertIdx >= buttons.size()) {
                        buttons.add(incomingTb);
                    } else {
                        buttons.add(insertIdx, incomingTb);
                    }
                }
            }
            return this;
        }

        /**
         * Clear the configured buttons.
         */
        public ToolbarBuilder clearButtons() {
            if (this.buttons == null) {
                this.buttons = new ArrayList<>();
            } else {
                this.buttons.clear();
            }
            return this;
        }

        /**
         * Value in pixels to be added to the X axis positioning of the toolbar.
         * Default: 0
         */
        public ToolbarBuilder diffLeft(int diffLeft) {
            this.diffLeft = diffLeft;
            return this;
        }

        /**
         * Value in pixels to be added to the Y axis positioning of the toolbar.
         * Default: -10
         */
        public ToolbarBuilder diffTop(int diffTop) {
            this.diffTop = diffTop;
            return this;
        }

        /**
         * CSS class added to the first button in the toolbar.
         * Default: 'medium-editor-button-first'
         */
        public ToolbarBuilder firstButtonClass(String firstButtonClass) {
            this.firstButtonClass = firstButtonClass;
            return this;
        }

        /**
         * CSS class added to the last button in the toolbar.
         * Default: 'medium-editor-button-last'
         */
        public ToolbarBuilder lastButtonClass(String lastButtonClass) {
            this.lastButtonClass = lastButtonClass;
            return this;
        }

        /**
         * Enables/disables standardizing how the beginning of a range is decided between browsers whenever the selected text is analyzed for updating toolbar buttons status.
         * Default: false
         */
        public ToolbarBuilder standardizeSelectionStart(boolean standardizeSelectionStart) {
            this.standardizeSelectionStart = standardizeSelectionStart;
            return this;
        }

        /**
         * Enable/disable the toolbar always displaying in the same location relative to the medium-editor element. Default: false
         */
        public ToolbarBuilder staticToolbar(boolean staticToolbar) {
            this.staticToolbar = staticToolbar;
            return this;
        }

        /**
         * When the static option is true, this aligns the static toolbar relative to the left of the medium-editor element.
         */
        public ToolbarBuilder alignLeft() {
            this.align = "left";
            return this;
        }

        /**
         * When the static option is true, this aligns the static toolbar relative to the center of the medium-editor element.
         */
        public ToolbarBuilder alignCenter() {
            this.align = "center";
            return this;
        }

        /**
         * When the static option is true, this aligns the static toolbar relative to the right of the medium-editor element.
         */
        public ToolbarBuilder alignRight() {
            this.align = "right";
            return this;
        }

        /**
         * When the static option is true, this enables/disables the toolbar "sticking" to the viewport and staying visible on the screen while the page scrolls.
         * Default: false
         */
        public ToolbarBuilder sticky(boolean sticky) {
            this.sticky = sticky;
            return this;
        }

        /**
         * When the sticky option is true, this set in pixel a top offset above the toolbar.
         * Default: 0
         */
        public ToolbarBuilder stickyTopOffset(boolean stickyTopOffset) {
            this.stickyTopOffset = stickyTopOffset;
            return this;
        }

        /**
         * When the static option is true, this enables/disables updating the state of the toolbar buttons even when the selection is collapsed (there is no selection, just a cursor).
         * Default: false
         */
        public ToolbarBuilder updateOnEmptySelection(boolean updateOnEmptySelection) {
            this.updateOnEmptySelection = updateOnEmptySelection;
            return this;
        }    

        public OptionsBuilder done() {
            return optionsBuilder;
        }

        @Override
        public Toolbar build() {
            return new Toolbar(this);
        }    

        @Override
        public JsonValue buildJson() {
            JsonObject map = Json.createObject();
            putNotNull(map, "allowMultiParagraphSelection", allowMultiParagraphSelection);
            if (buttons != null) {
                JsonArray btnList = Json.createArray();
                for (ToolbarButtonBuilder tbb : buttons) {
                    btnList.set(btnList.length(), tbb.buildJson());
                }
                map.put("buttons", btnList);
            }
            putNotNull(map, "diffLeft", diffLeft);
            putNotNull(map, "diffTop", diffLeft);
            putNotNull(map, "firstButtonClass", firstButtonClass);
            putNotNull(map, "lastButtonClass", lastButtonClass);
            putNotNull(map, "standardizeSelectionStart", standardizeSelectionStart);
            putNotNull(map, "static", staticToolbar);
            // TODO relativeContainer // Toolbar is appended relative to a given DOM-Node instead of appending it to the body and position it absolute.
            //      map.put("relativeContainer", null);
            putNotNull(map, "align", align);
            putNotNull(map, "sticky", sticky);
            putNotNull(map, "stickyTopOffset", stickyTopOffset);
            putNotNull(map, "updateOnEmptySelection", updateOnEmptySelection);
            return map;
        }

        ToolbarButtonBuilder getExistingTb(Buttons button) {
            String btnName = button.getName();
            if (buttons != null) {
                for (ToolbarButtonBuilder b : buttons) {
                    if (btnName.equals(b.getName())) {
                        return b;
                    }
                }
            }
            return null;
        }
    }

}
