package com.byteowls.vaadin.mediumeditor.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.byteowls.vaadin.mediumeditor.options.Toolbar.ToolbarBuilder;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;

public class ToolbarButton {

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
    }
    contentDefault = builder.iconFallback;
    aria = builder.tooltip;
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
    public static Map<BuildInButton, ToolbarButtonBuilder> BUILDIN;
    static {
      BUILDIN = new HashMap<BuildInButton, ToolbarButtonBuilder>();
      BUILDIN.put(BuildInButton.BOLD, ToolbarButton.builder()
          .icon(FontAwesome.BOLD)
          .name(BuildInButton.BOLD.getName())
          .tagNames("b", "strong")
          .style("font-weight", "700|bold")
          .useQueryState(true)
          .iconFallback("<b>B</b>"));
      BUILDIN.put(BuildInButton.ITALIC, ToolbarButton.builder()
          .icon(FontAwesome.ITALIC)
          .name(BuildInButton.ITALIC.getName())
          .tagNames("i", "em")
          .style("font-style", "italic")
          .useQueryState(true)
          .iconFallback("<b><i>I</i></b>"));
      BUILDIN.put(BuildInButton.UNDERLINE, ToolbarButton.builder()
          .icon(FontAwesome.UNDERLINE)
          .name(BuildInButton.UNDERLINE.getName())
          .tagNames("u")
          .style("text-decoration", "underline")
          .useQueryState(true)
          .iconFallback("<b><u>U</u></b>"));
      BUILDIN.put(BuildInButton.STRIKETHROUGH, ToolbarButton.builder()
          .icon(FontAwesome.STRIKETHROUGH)
          .name(BuildInButton.STRIKETHROUGH.getName())
          .tagNames("strike")
          .style("text-decoration", "line-through")
          .useQueryState(true)
          .iconFallback("<s>A</s>"));
      BUILDIN.put(BuildInButton.SUPERSCRIPT, ToolbarButton.builder()
          .icon(FontAwesome.SUPERSCRIPT)
          .name(BuildInButton.SUPERSCRIPT.getName())
          .tagNames("sup")
          .iconFallback("<b>x<sup>1</sup></b>"));
      BUILDIN.put(BuildInButton.SUBSCRIPT, ToolbarButton.builder()
          .icon(FontAwesome.SUBSCRIPT)
          .name(BuildInButton.SUBSCRIPT.getName())
          .tagNames("sub")
          .iconFallback("<b>x<sub>1</sub></b>"));
      BUILDIN.put(BuildInButton.IMAGE, ToolbarButton.builder()
          .icon(FontAwesome.IMAGE)
          .name(BuildInButton.IMAGE.getName())
          .tagNames("img")
          .iconFallback("<b>image</b>"));
      BUILDIN.put(BuildInButton.ORDEREDLIST, ToolbarButton.builder()
          .icon(FontAwesome.LIST_OL)
          .action("insertorderedlist")
          .name(BuildInButton.ORDEREDLIST.getName())
          .tagNames("ol")
          .useQueryState(true)
          .iconFallback("<b>1.</b>"));
      BUILDIN.put(BuildInButton.UNORDEREDLIST, ToolbarButton.builder()
          .icon(FontAwesome.LIST_UL)
          .action("insertunorderedlist")
          .name(BuildInButton.UNORDEREDLIST.getName())
          .tagNames("ol")
          .useQueryState(true)
          .iconFallback("<b>1.</b>"));
      
    }
    
    public static ToolbarButtonBuilder getBuildin(BuildInButton buildInButton) {
      return BUILDIN.get(buildInButton);
    }
    
    
    private ToolbarBuilder toolbarBuilder;

    private String name;
    private FontIcon icon;
    private String iconFallback;
    private String tooltip;
    private List<String> tagNames;
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
    
    public ToolbarButtonBuilder iconFallback(String iconFallback) {
      this.iconFallback = iconFallback;
      return this;
    }
    
    public ToolbarButtonBuilder tooltip(String tooltip) {
      this.tooltip = tooltip;
      return this;
    }
    
    public ToolbarButtonBuilder tagNames(String... tagNames) {
      if (this.tagNames == null) {
        this.tagNames = new ArrayList<String>();
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
        this.style = new HashMap<String, String>();
      }
      this.style.put("prop", prop);
      this.style.put("value", value);
      return this;
    }
    
    public ToolbarButtonBuilder classList(String... classList) {
      if (this.classList == null) {
        this.classList = new ArrayList<String>();
      }
      for (String c : classList) {
        this.classList.add(c);
      }
      return this;
    }
    
    public ToolbarButtonBuilder attr(String key, String value) {
      if (this.attrs == null) {
        this.attrs = new HashMap<String, String>();
      }
      this.attrs.put(key, value);
      return this;
    }

    public ToolbarButtonBuilder(ToolbarBuilder toolbarBuilder) {
      this.toolbarBuilder = toolbarBuilder;
    }
    
    public ToolbarButton build() {
      return new ToolbarButton(this);
    }
    
    public ToolbarBuilder and() {
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
