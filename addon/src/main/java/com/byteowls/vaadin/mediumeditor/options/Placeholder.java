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
    // reference to parent
    private OptionsBuilder optionsBuilder;

    private String text;
    
    public PlaceholderBuilder(OptionsBuilder optionsBuilder) {
      this.optionsBuilder = optionsBuilder;
    }
    
    public PlaceholderBuilder text(String text) {
      this.text = text;
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
      return map;
    }
    
  }

}
