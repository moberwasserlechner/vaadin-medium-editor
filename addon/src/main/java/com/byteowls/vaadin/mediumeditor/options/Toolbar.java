package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;
import com.byteowls.vaadin.mediumeditor.options.ToolbarButton.ToolbarButtonBuilder;

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
  
  public static class ToolbarBuilder {
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
    
    /**
     * Set the translations to the existing buttons. The order is important.
     * @param translations
     * @return
     */
    public ToolbarBuilder buttonTranslations(String... translations) {
      if (translations != null) {
        int cnt = 0;
        int len = translations.length;
        
        for (ToolbarButtonBuilder b : buttons) {
          if (cnt < len) {
            b.aria(translations[cnt]);
          }
          cnt++;
        }
      }
      return this;
    }

    
    public ToolbarBuilder add(BuildInButton button, String tooltip) {
      ToolbarButtonBuilder tb = getExistingTb(button); 
      if (tb == null) {
        tb = ToolbarButtonBuilder.BUILDIN.get(button);
        buttons.add(tb);
      }
      tb.aria(tooltip);
      return this;
    }
    
    public ToolbarBuilder buttons(BuildInButton... buttons) {
      if (buttons != null) {
        if (this.buttons == null) {
          this.buttons = new ArrayList<>();
        }
        for (BuildInButton b : buttons) {
          ToolbarButtonBuilder tbb = ToolbarButtonBuilder.BUILDIN.get(b);
          if (tbb != null) {
            this.buttons.add(tbb);
          }
        }
      }
      return this;
    }
    
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
    
    public ToolbarBuilder buttonBefore(BuildInButton before, BuildInButton incoming) {
      if (incoming != null) {
        if (before == null) {
          buttons(incoming);
        } else {
          ToolbarButtonBuilder beforeTb = ToolbarButtonBuilder.BUILDIN.get(before);
          ToolbarButtonBuilder incomingTb = ToolbarButtonBuilder.BUILDIN.get(incoming);
          
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
    
    public ToolbarBuilder buttonAfter(BuildInButton after, BuildInButton incoming) {
      if (incoming != null) {
        if (after == null) {
          buttons(incoming);
        } else {
          ToolbarButtonBuilder afterTb = ToolbarButtonBuilder.BUILDIN.get(after);
          ToolbarButtonBuilder incomingTb = ToolbarButtonBuilder.BUILDIN.get(incoming);
          
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
    
    public ToolbarBuilder clearButtons() {
      if (this.buttons == null) {
        this.buttons = new ArrayList<>();
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
    
    public OptionsBuilder done() {
      return optionsBuilder;
    }

    Toolbar build() {
      return new Toolbar(this);
    }
    
    ToolbarButtonBuilder getExistingTb(BuildInButton button) {
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
