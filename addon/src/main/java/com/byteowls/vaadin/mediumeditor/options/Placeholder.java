package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

public class Placeholder implements Serializable {
  
  private static final long serialVersionUID = 7188275928268373605L;

  public String text;
  
  private Placeholder(PlaceholderBuilder builder) {
    text = builder.text;
  }
  
  public static PlaceholderBuilder builder(OptionsBuilder optionsBuilder) {
    return new PlaceholderBuilder(optionsBuilder);
  }
  
  public static class PlaceholderBuilder {
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
    
    public OptionsBuilder and() {
      return optionsBuilder;
    }

    Placeholder build() {
      return new Placeholder(this);
    }
    
  }

}
