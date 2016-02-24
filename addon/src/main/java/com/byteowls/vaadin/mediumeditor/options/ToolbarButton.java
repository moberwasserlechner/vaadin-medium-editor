package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.byteowls.vaadin.mediumeditor.options.Toolbar.ToolbarBuilder;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;

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

  public static class ToolbarButtonBuilder {
    private static Map<BuildInButton, ToolbarButtonBuilder> BUILDIN;
    static {
      BUILDIN = new HashMap<BuildInButton, ToolbarButtonBuilder>();
      BUILDIN.put(BuildInButton.BOLD, ToolbarButton.builder()
          .icon(FontAwesome.BOLD)
          .aria("bold")
          .name(BuildInButton.BOLD.getName())
          .tagNames("b", "strong")
          .style("font-weight", "700|bold")
          .useQueryState(true)
          .iconFallback("<b>B</b>"));
      BUILDIN.put(BuildInButton.ITALIC, ToolbarButton.builder()
          .icon(FontAwesome.ITALIC)
          .aria("italic")
          .name(BuildInButton.ITALIC.getName())
          .tagNames("i", "em")
          .style("font-style", "italic")
          .useQueryState(true)
          .iconFallback("<b><i>I</i></b>"));
      BUILDIN.put(BuildInButton.UNDERLINE, ToolbarButton.builder()
          .icon(FontAwesome.UNDERLINE)
          .aria("underline")
          .name(BuildInButton.UNDERLINE.getName())
          .tagNames("u")
          .style("text-decoration", "underline")
          .useQueryState(true)
          .iconFallback("<b><u>U</u></b>"));
      BUILDIN.put(BuildInButton.STRIKETHROUGH, ToolbarButton.builder()
          .icon(FontAwesome.STRIKETHROUGH)
          .aria("strikethrough")
          .name(BuildInButton.STRIKETHROUGH.getName())
          .tagNames("strike")
          .style("text-decoration", "line-through")
          .useQueryState(true)
          .iconFallback("<s>A</s>"));
      BUILDIN.put(BuildInButton.SUPERSCRIPT, ToolbarButton.builder()
          .icon(FontAwesome.SUPERSCRIPT)
          .aria("superscript")
          .name(BuildInButton.SUPERSCRIPT.getName())
          .tagNames("sup")
          .iconFallback("<b>x<sup>1</sup></b>"));
      BUILDIN.put(BuildInButton.SUBSCRIPT, ToolbarButton.builder()
          .icon(FontAwesome.SUBSCRIPT)
          .aria("subscript")
          .name(BuildInButton.SUBSCRIPT.getName())
          .tagNames("sub")
          .iconFallback("<b>x<sub>1</sub></b>"));
      BUILDIN.put(BuildInButton.IMAGE, ToolbarButton.builder()
          .icon(FontAwesome.IMAGE)
          .aria("image")
          .name(BuildInButton.IMAGE.getName())
          .tagNames("img")
          .iconFallback("<b>image</b>"));

      BUILDIN.put(BuildInButton.ORDEREDLIST, ToolbarButton.builder()
          .icon(FontAwesome.LIST_OL)
          .aria("orderedlist")
          .action("insertorderedlist")
          .name(BuildInButton.ORDEREDLIST.getName())
          .tagNames("ol")
          .useQueryState(true)
          .iconFallback("<b>1.</b>"));
      BUILDIN.put(BuildInButton.UNORDEREDLIST, ToolbarButton.builder()
          .icon(FontAwesome.LIST_UL)
          .aria("unorderedlist")
          .action("insertunorderedlist")
          .name(BuildInButton.UNORDEREDLIST.getName())
          .tagNames("ol")
          .useQueryState(true)
          .iconFallback("<b>&bull;</b>"));

      BUILDIN.put(BuildInButton.INDENT, ToolbarButton.builder()
          .icon(FontAwesome.INDENT)
          .aria("indent")
          .name(BuildInButton.INDENT.getName())
          .iconFallback("<b>&rarr;</b>"));
      BUILDIN.put(BuildInButton.OUTDENT, ToolbarButton.builder()
          .icon(FontAwesome.OUTDENT)
          .aria("outdent")
          .name(BuildInButton.OUTDENT.getName())
          .iconFallback("<b>&larr;</b>"));

      BUILDIN.put(BuildInButton.JUSTIFY_FULL, ToolbarButton.builder()
          .icon(FontAwesome.ALIGN_JUSTIFY)
          .aria("alignjustify")
          .style("text-align", "justify")
          .name(BuildInButton.JUSTIFY_FULL.getName())
          .iconFallback("<b>J</b>"));
      BUILDIN.put(BuildInButton.JUSTIFY_LEFT, ToolbarButton.builder()
          .icon(FontAwesome.ALIGN_LEFT)
          .aria("alignleft")
          .style("text-align", "left")
          .name(BuildInButton.JUSTIFY_LEFT.getName())
          .iconFallback("<b>L</b>"));
      BUILDIN.put(BuildInButton.JUSTIFY_CENTER, ToolbarButton.builder()
          .icon(FontAwesome.ALIGN_CENTER)
          .aria("aligncenter")
          .style("text-align", "center")
          .name(BuildInButton.JUSTIFY_CENTER.getName())
          .iconFallback("<b>C</b>"));
      BUILDIN.put(BuildInButton.JUSTIFY_RIGHT, ToolbarButton.builder()
          .icon(FontAwesome.ALIGN_RIGHT)
          .aria("alignright")
          .style("text-align", "right")
          .name(BuildInButton.JUSTIFY_RIGHT.getName())
          .iconFallback("<b>R</b>"));

      BUILDIN.put(BuildInButton.REMOVE_FORMAT, ToolbarButton.builder()
          .icon(FontAwesome.ERASER)
          .aria("removeformatting")
          .name(BuildInButton.REMOVE_FORMAT.getName())
          .iconFallback("<b>&ldquo;</b>")); 

      BUILDIN.put(BuildInButton.QUOTE, ToolbarButton.builder()
          .icon(FontAwesome.QUOTE_RIGHT)
          .action("append-blockquote")
          .aria("blockquote")
          .tagNames("blockquote")
          .name(BuildInButton.QUOTE.getName())
          .iconFallback("<b>0101</b>"));  

      BUILDIN.put(BuildInButton.PRE, ToolbarButton.builder()
          .icon(FontAwesome.CODE)
          .action("append-pre")
          .aria("preformattedtext")
          .tagNames("pre")
          .name(BuildInButton.PRE.getName())
          .iconFallback("<b>0101</b>"));

      BUILDIN.put(BuildInButton.H1, ToolbarButton.builder()
          .icon(FontAwesome.HEADER, "<sup>1</sup>")
          .action("append-h1")
          .aria("headertype1")
          .tagNames("h1")
          .name(BuildInButton.H1.getName())
          .iconFallback("<b>H1</b>"));

      BUILDIN.put(BuildInButton.H2, ToolbarButton.builder()
          .icon(FontAwesome.HEADER, "<sup>2</sup>")
          .action("append-h2")
          .aria("headertype2")
          .tagNames("h2")
          .name(BuildInButton.H2.getName())
          .iconFallback("<b>H2</b>"));

      BUILDIN.put(BuildInButton.H3, ToolbarButton.builder()
          .icon(FontAwesome.HEADER, "<sup>3</sup>")
          .action("append-h3")
          .aria("headertype3")
          .tagNames("h3")
          .name(BuildInButton.H3.getName())
          .iconFallback("<b>H3</b>"));

      BUILDIN.put(BuildInButton.H4, ToolbarButton.builder()
          .icon(FontAwesome.HEADER, "<sup>4</sup>")
          .action("append-h4")
          .aria("headertype4")
          .tagNames("h4")
          .name(BuildInButton.H4.getName())
          .iconFallback("<b>H4</b>"));

      BUILDIN.put(BuildInButton.H5, ToolbarButton.builder()
          .icon(FontAwesome.HEADER, "<sup>5</sup>")
          .action("append-h5")
          .aria("headertype5")
          .tagNames("h5")
          .name(BuildInButton.H5.getName())
          .iconFallback("<b>H5</b>"));

      BUILDIN.put(BuildInButton.H6, ToolbarButton.builder()
          .icon(FontAwesome.HEADER, "<sup>6</sup>")
          .action("append-h6")
          .aria("headertype6")
          .tagNames("h6")
          .name(BuildInButton.H6.getName())
          .iconFallback("<b>H6</b>"));
      
      // extension
      BUILDIN.put(BuildInButton.ANCHOR, ToolbarButton.builder()
          .icon(FontAwesome.LINK)
          .action("createLink")
          .aria("link")
          .tagNames("a")
          .name(BuildInButton.ANCHOR.getName())
          .iconFallback("<b>#</b>"));
    }

    /**
     * Gets a copy of the buildin button, which might be changed.
     * @param buildInButton the {@link BuildInButton} enum
     * @return a copy of the buildin button builder.
     */
    public static ToolbarButtonBuilder getBuildin(BuildInButton buildInButton) {
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
    private boolean useQueryState;
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

    protected ToolbarButton build() {
      return new ToolbarButton(this);
    }

    public ToolbarBuilder done() {
      return this.toolbarBuilder;
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
