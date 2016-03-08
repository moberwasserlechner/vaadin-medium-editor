package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class Anchor implements Serializable {
  
  private static final long serialVersionUID = -9143692744706396347L;
  
  public String customClassOption;
  public String customClassOptionText;
  public Boolean linkValidation;
  public String placeholderText;
  public Boolean targetCheckbox;
  public String targetCheckboxText;
  
  private Anchor(AnchorBuilder builder) {
    customClassOption = builder.customClassOption;
    customClassOptionText = builder.customClassOptionText;
    linkValidation = builder.linkValidation;
    placeholderText = builder.placeholderText;
    targetCheckbox = builder.targetCheckbox;
    targetCheckboxText = builder.targetCheckboxText;
  }
  
  public static AnchorBuilder builder(OptionsBuilder optionsBuilder) {
    return new AnchorBuilder(optionsBuilder);
  }
  
  public static class AnchorBuilder extends AbstractBuilder<Anchor> {
    // reference to parent
    private OptionsBuilder optionsBuilder;

    private String customClassOption;
    private String customClassOptionText;
    private Boolean linkValidation;
    private String placeholderText;
    private Boolean targetCheckbox;
    private String targetCheckboxText;
    
    public AnchorBuilder(OptionsBuilder optionsBuilder) {
      this.optionsBuilder = optionsBuilder;
    }
    
    public AnchorBuilder customClassOption(String customClassOption) {
      this.customClassOption = customClassOption;
      return this;
    }
    
    public AnchorBuilder customClassOptionText(String customClassOptionText) {
      this.customClassOptionText = customClassOptionText;
      return this;
    }

    public AnchorBuilder linkValidation(boolean linkValidation) {
      this.linkValidation = linkValidation;
      return this;
    }
    
    
    public AnchorBuilder placeholderText(String placeholderText) {
      this.placeholderText = placeholderText;
      return this;
    }
    
    public AnchorBuilder targetCheckbox(boolean targetCheckbox) {
      this.targetCheckbox = targetCheckbox;
      return this;
    }
    
    public AnchorBuilder targetCheckboxText(String targetCheckboxText) {
      this.targetCheckboxText = targetCheckboxText;
      return this;
    }
    
    public OptionsBuilder done() {
      return optionsBuilder;
    }

    @Override
    public Anchor build() {
      return new Anchor(this);
    }
    
    @Override
    public JsonValue buildJson() {
      JsonObject map = Json.createObject();
      putNotNull(map, "customClassOption", customClassOption);
      putNotNull(map, "customClassOptionText", customClassOptionText);
      putNotNull(map, "linkValidation", linkValidation);
      putNotNull(map, "placeholderText", placeholderText);
      putNotNull(map, "targetCheckbox", targetCheckbox);
      putNotNull(map, "targetCheckboxText", targetCheckboxText);
      return map;
    }
  }

}
