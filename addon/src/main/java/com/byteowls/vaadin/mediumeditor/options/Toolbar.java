package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

public class Toolbar implements Serializable {
  
  private static final long serialVersionUID = -3318254088223351177L;

  public Boolean allowMultiParagraphSelection;
  public List<Object> buttons;
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
    buttons = builder.buttons;
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
  
  public static class ToolbarBuilder {
    private OptionsBuilder optionsBuilder;
    
    private Boolean allowMultiParagraphSelection;
    private List<Object> buttons;
    private Integer diffLeft = null;
    private Integer diffTop = null;
    private String firstButtonClass;
    private String lastButtonClass;
    private Boolean standardizeSelectionStart;
    private Boolean staticToolbar;
    private String align;
    private Boolean sticky;
    private Boolean updateOnEmptySelection;
    
    
    public ToolbarBuilder(OptionsBuilder optionsBuilder) {
      this.optionsBuilder = optionsBuilder;
    }
    
    public ToolbarBuilder allowMultiParagraphSelection(boolean allowMultiParagraphSelection) {
      this.allowMultiParagraphSelection = allowMultiParagraphSelection;
      return this;
    }
    
    public ToolbarBuilder defaultButtons() {
      buttons(BuildInButton.BOLD, BuildInButton.ITALIC, BuildInButton.UNDERLINE, BuildInButton.ANCHOR, BuildInButton.H2, BuildInButton.H3, BuildInButton.QUOTE);
      return this;
    }
    
    public ToolbarBuilder buttons(BuildInButton... buttons) {
      if (buttons != null) {
        if (this.buttons == null) {
          this.buttons = new ArrayList<Object>();
        }
        for (BuildInButton b : buttons) {
          this.buttons.add(b.getName());
        }
      }
      return this;
    }
    
    public ToolbarBuilder buttonBefore(BuildInButton before, BuildInButton incoming) {
      if (incoming != null) {
        if (before == null) {
          buttons(incoming);
        } else {
          int beforeIdx = buttons.indexOf(before.getName());
          int insertIdx = --beforeIdx;
          if (insertIdx < 0) {
            insertIdx = 0;
          }
          buttons.add(insertIdx, incoming.getName());
        }
      }
      return this;
    }
    
    public ToolbarBuilder buttonAfter(BuildInButton after, BuildInButton incoming) {
      if (incoming != null) {
        if (after == null) {
          buttons(incoming);
        } else {
          int afterIdx = buttons.indexOf(after.getName());
          int insertIdx = afterIdx++;
          if (insertIdx >= buttons.size()) {
            buttons.add(incoming.getName());
          } else {
            buttons.add(insertIdx, incoming.getName());
          }
        }
      }
      return this;
    }
    
    public ToolbarBuilder clearButtons() {
      if (this.buttons == null) {
        this.buttons = new ArrayList<Object>();
      } else {
        this.buttons.clear();
      }
      return this;
    }
    
    public ToolbarBuilder diffLeft(int diffLeft) {
      this.diffLeft = diffLeft;
      return this;
    }
    
    public ToolbarBuilder diffTop(int diffTop) {
      this.diffTop = diffTop;
      return this;
    }
    
    public ToolbarBuilder firstButtonClass(String firstButtonClass) {
      this.firstButtonClass = firstButtonClass;
      return this;
    }
    
    public ToolbarBuilder lastButtonClass(String lastButtonClass) {
      this.lastButtonClass = lastButtonClass;
      return this;
    }
    
    public ToolbarBuilder standardizeSelectionStart(boolean standardizeSelectionStart) {
      this.standardizeSelectionStart = standardizeSelectionStart;
      return this;
    }
    
    public ToolbarBuilder staticToolbar(boolean staticToolbar) {
      this.staticToolbar = staticToolbar;
      return this;
    }
    
    public ToolbarBuilder alignLeft() {
      this.align = "left";
      return this;
    }

    public ToolbarBuilder alignCenter() {
      this.align = "center";
      return this;
    }
    
    public ToolbarBuilder alignRight() {
      this.align = "right";
      return this;
    }
    
    public ToolbarBuilder sticky(boolean sticky) {
      this.sticky = sticky;
      return this;
    }
    
    public ToolbarBuilder updateOnEmptySelection(boolean updateOnEmptySelection) {
      this.updateOnEmptySelection = updateOnEmptySelection;
      return this;
    }    
    
    public OptionsBuilder and() {
      return optionsBuilder;
    }

    Toolbar build() {
      return new Toolbar(this);
    }
  }
  
}
