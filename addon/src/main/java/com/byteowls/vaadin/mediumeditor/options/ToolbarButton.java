package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.byteowls.vaadin.mediumeditor.options.Toolbar.ToolbarBuilder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontIcon;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class ToolbarButton implements Serializable {

    private static final long serialVersionUID = -8549658939678510199L;

    public String name;
    public String action;
    public String aria;
    public List<String> tagNames;
    public Map<String, String> style;
    public Boolean useQueryState;
    public String contentDefault;
    public String contentFA;
    public List<String> classList;
    public Map<String, String> attrs;

    private ToolbarButton(ToolbarButtonBuilder builder) {
        FontIcon icon = builder.icon;
        if (icon != null) {
            contentFA = icon.getHtml();
            if (builder.iconText != null) {
                contentFA += builder.iconText;
            }
        }
        contentDefault = builder.iconFallback;
        if (builder.customTranslation) {
            aria = builder.aria;
        } else {
            // get buildin translations
            aria = builder.toolbarBuilder.getOptionsBuilder().getTranslation(builder.aria);
        }
        name = builder.name;
        action = builder.action;
        if (action == null) {
            action = name;
        }
        tagNames = builder.tagNames;
        style = builder.style;
        useQueryState = builder.useQueryState;
        classList = builder.classList;
        attrs = builder.attrs;
    }

    public static ToolbarButtonBuilder builder() {
        return new ToolbarButtonBuilder(null);
    }

    public static ToolbarButtonBuilder builder(ToolbarBuilder toolbarBuilder) {
        return new ToolbarButtonBuilder(toolbarBuilder);
    }

    public static class ToolbarButtonBuilder extends AbstractBuilder<ToolbarButton> {
        private static Map<Buttons, ToolbarButtonBuilder> BUILDIN;
        static {
            BUILDIN = new HashMap<Buttons, ToolbarButtonBuilder>();
            BUILDIN.put(Buttons.BOLD, ToolbarButton.builder()
                    .icon(VaadinIcons.BOLD)
                    .aria("bold")
                    .name(Buttons.BOLD.getName())
                    .tagNames("b", "strong")
                    .style("font-weight", "700|bold")
                    .useQueryState(true)
                    .iconFallback("<b>B</b>"));
            BUILDIN.put(Buttons.ITALIC, ToolbarButton.builder()
                    .icon(VaadinIcons.ITALIC)
                    .aria("italic")
                    .name(Buttons.ITALIC.getName())
                    .tagNames("i", "em")
                    .style("font-style", "italic")
                    .useQueryState(true)
                    .iconFallback("<b><i>I</i></b>"));
            BUILDIN.put(Buttons.UNDERLINE, ToolbarButton.builder()
                    .icon(VaadinIcons.UNDERLINE)
                    .aria("underline")
                    .name(Buttons.UNDERLINE.getName())
                    .tagNames("u")
                    .style("text-decoration", "underline")
                    .useQueryState(true)
                    .iconFallback("<b><u>U</u></b>"));
            BUILDIN.put(Buttons.STRIKETHROUGH, ToolbarButton.builder()
                    .icon(VaadinIcons.STRIKETHROUGH)
                    .aria("strikethrough")
                    .name(Buttons.STRIKETHROUGH.getName())
                    .tagNames("strike")
                    .style("text-decoration", "line-through")
                    .useQueryState(true)
                    .iconFallback("<s>A</s>"));
            BUILDIN.put(Buttons.SUPERSCRIPT, ToolbarButton.builder()
                    .icon(VaadinIcons.SUPERSCRIPT)
                    .aria("superscript")
                    .name(Buttons.SUPERSCRIPT.getName())
                    .tagNames("sup")
                    .iconFallback("<b>x<sup>1</sup></b>"));
            BUILDIN.put(Buttons.SUBSCRIPT, ToolbarButton.builder()
                    .icon(VaadinIcons.SUBSCRIPT)
                    .aria("subscript")
                    .name(Buttons.SUBSCRIPT.getName())
                    .tagNames("sub")
                    .iconFallback("<b>x<sub>1</sub></b>"));
            //      BUILDIN.put(Buttons.IMAGE, ToolbarButton.builder()
            //          .icon(VaadinIcons.IMAGE)
            //          .aria("image")
            //          .name(Buttons.IMAGE.getName())
            //          .tagNames("img")
            //          .iconFallback("<b>image</b>"));

            BUILDIN.put(Buttons.ORDEREDLIST, ToolbarButton.builder()
                    .icon(VaadinIcons.LIST_OL)
                    .aria("orderedlist")
                    .action("insertorderedlist")
                    .name(Buttons.ORDEREDLIST.getName())
                    .tagNames("ol")
                    .useQueryState(true)
                    .iconFallback("<b>1.</b>"));
            BUILDIN.put(Buttons.UNORDEREDLIST, ToolbarButton.builder()
                    .icon(VaadinIcons.LIST_UL)
                    .aria("unorderedlist")
                    .action("insertunorderedlist")
                    .name(Buttons.UNORDEREDLIST.getName())
                    .tagNames("ol")
                    .useQueryState(true)
                    .iconFallback("<b>&bull;</b>"));

            BUILDIN.put(Buttons.INDENT, ToolbarButton.builder()
                    .icon(VaadinIcons.INDENT)
                    .aria("indent")
                    .name(Buttons.INDENT.getName())
                    .iconFallback("<b>&rarr;</b>"));
            BUILDIN.put(Buttons.OUTDENT, ToolbarButton.builder()
                    .icon(VaadinIcons.DEINDENT)
                    .aria("outdent")
                    .name(Buttons.OUTDENT.getName())
                    .iconFallback("<b>&larr;</b>"));

            BUILDIN.put(Buttons.JUSTIFY_FULL, ToolbarButton.builder()
                    .icon(VaadinIcons.ALIGN_JUSTIFY)
                    .aria("alignjustify")
                    .style("text-align", "justify")
                    .name(Buttons.JUSTIFY_FULL.getName())
                    .iconFallback("<b>J</b>"));
            BUILDIN.put(Buttons.JUSTIFY_LEFT, ToolbarButton.builder()
                    .icon(VaadinIcons.ALIGN_LEFT)
                    .aria("alignleft")
                    .style("text-align", "left")
                    .name(Buttons.JUSTIFY_LEFT.getName())
                    .iconFallback("<b>L</b>"));
            BUILDIN.put(Buttons.JUSTIFY_CENTER, ToolbarButton.builder()
                    .icon(VaadinIcons.ALIGN_CENTER)
                    .aria("aligncenter")
                    .style("text-align", "center")
                    .name(Buttons.JUSTIFY_CENTER.getName())
                    .iconFallback("<b>C</b>"));
            BUILDIN.put(Buttons.JUSTIFY_RIGHT, ToolbarButton.builder()
                    .icon(VaadinIcons.ALIGN_RIGHT)
                    .aria("alignright")
                    .style("text-align", "right")
                    .name(Buttons.JUSTIFY_RIGHT.getName())
                    .iconFallback("<b>R</b>"));

            BUILDIN.put(Buttons.REMOVE_FORMAT, ToolbarButton.builder()
                    .icon(VaadinIcons.ERASER)
                    .aria("removeformatting")
                    .action("removeFormat")
                    .name(Buttons.REMOVE_FORMAT.getName())
                    .iconFallback("<b>X</b>"));

            BUILDIN.put(Buttons.QUOTE, ToolbarButton.builder()
                    .icon(VaadinIcons.QUOTE_RIGHT)
                    .action("append-blockquote")
                    .aria("blockquote")
                    .tagNames("blockquote")
                    .name(Buttons.QUOTE.getName())
                    .iconFallback("<b>&ldquo;</b>"));  

            BUILDIN.put(Buttons.PRE, ToolbarButton.builder()
                    .icon(VaadinIcons.CODE)
                    .action("append-pre")
                    .aria("preformattedtext")
                    .tagNames("pre")
                    .name(Buttons.PRE.getName())
                    .iconFallback("<b>0101</b>"));

            BUILDIN.put(Buttons.H1, ToolbarButton.builder()
                    .icon(VaadinIcons.HEADER, "<sup>1</sup>")
                    .action("append-h1")
                    .aria("headertype1")
                    .tagNames("h1")
                    .name(Buttons.H1.getName())
                    .iconFallback("<b>H1</b>"));

            BUILDIN.put(Buttons.H2, ToolbarButton.builder()
                    .icon(VaadinIcons.HEADER, "<sup>2</sup>")
                    .action("append-h2")
                    .aria("headertype2")
                    .tagNames("h2")
                    .name(Buttons.H2.getName())
                    .iconFallback("<b>H2</b>"));

            BUILDIN.put(Buttons.H3, ToolbarButton.builder()
                    .icon(VaadinIcons.HEADER, "<sup>3</sup>")
                    .action("append-h3")
                    .aria("headertype3")
                    .tagNames("h3")
                    .name(Buttons.H3.getName())
                    .iconFallback("<b>H3</b>"));

            BUILDIN.put(Buttons.H4, ToolbarButton.builder()
                    .icon(VaadinIcons.HEADER, "<sup>4</sup>")
                    .action("append-h4")
                    .aria("headertype4")
                    .tagNames("h4")
                    .name(Buttons.H4.getName())
                    .iconFallback("<b>H4</b>"));

            BUILDIN.put(Buttons.H5, ToolbarButton.builder()
                    .icon(VaadinIcons.HEADER, "<sup>5</sup>")
                    .action("append-h5")
                    .aria("headertype5")
                    .tagNames("h5")
                    .name(Buttons.H5.getName())
                    .iconFallback("<b>H5</b>"));

            BUILDIN.put(Buttons.H6, ToolbarButton.builder()
                    .icon(VaadinIcons.HEADER, "<sup>6</sup>")
                    .action("append-h6")
                    .aria("headertype6")
                    .tagNames("h6")
                    .name(Buttons.H6.getName())
                    .iconFallback("<b>H6</b>"));

            BUILDIN.put(Buttons.HTML, ToolbarButton.builder()
                    .icon(VaadinIcons.CODE)
                    .aria("html")
                    .action("html")
                    .name(Buttons.HTML.getName())
                    .iconFallback("<b>html</b>")); 

            // extension
            BUILDIN.put(Buttons.ANCHOR, ToolbarButton.builder()
                    .icon(VaadinIcons.LINK)
                    .action("createLink")
                    .aria("link")
                    .tagNames("a")
                    .name(Buttons.ANCHOR.getName())
                    .iconFallback("<b>#</b>"));

            BUILDIN.put(Buttons.FONTSIZE, ToolbarButton.builder()
                    .icon(VaadinIcons.TEXT_HEIGHT)
                    .action("fontSize")
                    .aria("fontsize")
                    .name(Buttons.FONTSIZE.getName())
                    .iconFallback("&#xB1;"));
        }

        /**
         * Gets a copy of the buildin button, which might be changed.
         * @param buildInButton the {@link Buttons} enum
         * @return a copy of the buildin button builder.
         */
        public static ToolbarButtonBuilder getBuildin(Buttons buildInButton) {
            ToolbarButtonBuilder buildinBuilder = BUILDIN.get(buildInButton);
            return buildinBuilder.copy();
        }

        private ToolbarButtonBuilder copy() {
            ToolbarButtonBuilder b = ToolbarButton.builder(toolbarBuilder);
            b.icon = this.icon;
            b.iconText = this.iconText;
            b.action = this.action;
            b.customTranslation = this.customTranslation;
            b.aria = this.aria;
            if (this.tagNames != null) {
                b.tagNames = new ArrayList<>(this.tagNames);
            }
            b.name = this.name;
            b.useQueryState = this.useQueryState;
            b.iconFallback = this.iconFallback;
            if (this.style != null) {
                b.style = new HashMap<>(this.style);
            }
            if (this.classList != null) {
                b.classList = new ArrayList<>(this.classList);
            }
            if (this.attrs != null) {
                b.attrs = new HashMap<>(this.attrs);
            }
            return b;
        }

        private ToolbarBuilder toolbarBuilder;

        private String name;
        private FontIcon icon;
        private String iconText;
        private String iconFallback;
        private boolean customTranslation;
        private String aria;
        private List<String> tagNames = new ArrayList<>();
        private String action;
        private Map<String, String> style;
        private Boolean useQueryState;
        private List<String> classList;
        private Map<String, String> attrs;

        public String getName() {
            return name;
        }

        public ToolbarButtonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ToolbarButtonBuilder icon(FontIcon icon) {
            this.icon = icon;
            return this;
        }
        public ToolbarButtonBuilder icon(FontIcon icon, String iconText) {
            this.icon = icon;
            this.iconText = iconText;
            return this;
        }

        public ToolbarButtonBuilder iconFallback(String iconFallback) {
            this.iconFallback = iconFallback;
            return this;
        }

        public ToolbarButtonBuilder contentDefault(String contentDefault) {
            this.iconFallback = contentDefault;
            return this;
        }

        public ToolbarButtonBuilder aria(String aria) {
            return aria(aria, false);
        }

        public ToolbarButtonBuilder aria(String aria, boolean customTranslation) {
            this.customTranslation = customTranslation;
            this.aria = aria;
            return this;
        }

        public ToolbarButtonBuilder tagNames(String... tagNames) {
            if (this.tagNames == null) {
                this.tagNames = new ArrayList<>();
            }
            for (String n : tagNames) {
                this.tagNames.add(n);
            }
            return this;
        }

        public ToolbarButtonBuilder useQueryState(boolean useQueryState) {
            this.useQueryState = useQueryState;
            return this;
        }

        public ToolbarButtonBuilder action(String action) {
            this.action = action;
            return this;
        }

        public ToolbarButtonBuilder style(String prop, String value) {
            if (this.style == null) {
                this.style = new HashMap<>();
            }
            this.style.put("prop", prop);
            this.style.put("value", value);
            return this;
        }

        public ToolbarButtonBuilder classList(String... classList) {
            if (this.classList == null) {
                this.classList = new ArrayList<>();
            }
            for (String c : classList) {
                this.classList.add(c);
            }
            return this;
        }

        public ToolbarButtonBuilder attr(String key, String value) {
            if (this.attrs == null) {
                this.attrs = new HashMap<>();
            }
            this.attrs.put(key, value);
            return this;
        }

        public ToolbarButtonBuilder(ToolbarBuilder toolbarBuilder) {
            this.toolbarBuilder = toolbarBuilder;
        }

        public ToolbarButtonBuilder parentBuilder(ToolbarBuilder toolbarBuilder) {
            this.toolbarBuilder = toolbarBuilder;
            return this;
        }

        @Override
        public ToolbarButton build() {
            return new ToolbarButton(this);
        }

        public ToolbarBuilder done() {
            return this.toolbarBuilder;
        }

        @Override
        public JsonValue buildJson() {
            JsonObject map = Json.createObject();
            if (icon != null) {
                String contentFA = icon.getHtml();
                if (iconText != null) {
                    contentFA += iconText;
                }
                putNotNull(map, "contentFA", contentFA);
            }
            putNotNull(map, "contentDefault", iconFallback);
            if (customTranslation) {
                putNotNull(map, "aria", aria);
            } else {
                // get buildin translations
                putNotNull(map, "aria", toolbarBuilder.getOptionsBuilder().getTranslation(aria));
            }
            putNotNull(map, "name", name);
            putNotNull(map, "action", action == null ? name : action);
            putNotNull(map, "tagNames", tagNames);
            putNotNull(map, "style", style);
            putNotNull(map, "useQueryState", useQueryState);
            putNotNull(map, "classList", classList);
            putNotNull(map, "attrs", attrs);
            return map;
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ToolbarButton other = (ToolbarButton) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
