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
    
    public PasteHandlerBuilder(OptionsBuilder optionsBuilder) {
      this.optionsBuilder = optionsBuilder;
    }
    
    public PasteHandlerBuilder forcePlainText(boolean forcePlainText) {
      this.forcePlainText = forcePlainText;
      return this;
    }
    
    public PasteHandlerBuilder cleanPastedHTML(boolean cleanPastedHTML) {
      this.cleanPastedHTML = cleanPastedHTML;
      return this;
    }
    
    public PasteHandlerBuilder cleanAttrs(String... cleanAttrs) {
      if (this.cleanAttrs == null) {
        this.cleanAttrs = new ArrayList<>();
      }
      for (String a : cleanAttrs) {
        this.cleanAttrs.add(a);
      }
      return this;
    }
    
    public PasteHandlerBuilder cleanTags(String... cleanTags) {
      if (this.cleanTags == null) {
        this.cleanTags = new ArrayList<>();
      }
      for (String t : cleanTags) {
        this.cleanTags.add(t);
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
      return map;
    }
    
  }

}
