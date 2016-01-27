package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

public class AnchorPreview implements Serializable {
  
  private static final long serialVersionUID = -564862914858140461L;

  public Integer hideDelay = null;
  public String previewValueSelector;
  public Boolean showWhenToolbarIsVisible;
  
  private AnchorPreview(AnchorPreviewBuilder builder) {
    hideDelay = builder.hideDelay;
    previewValueSelector = builder.previewValueSelector;
    showWhenToolbarIsVisible = builder.showWhenToolbarIsVisible;
  }
  
  public static AnchorPreviewBuilder builder(OptionsBuilder optionsBuilder) {
    return new AnchorPreviewBuilder(optionsBuilder);
  }
  
  public static class AnchorPreviewBuilder {
    // reference to parent
    private OptionsBuilder optionsBuilder;

    private Integer hideDelay = null;
    private String previewValueSelector;
    private Boolean showWhenToolbarIsVisible;
    
    public AnchorPreviewBuilder(OptionsBuilder optionsBuilder) {
      this.optionsBuilder = optionsBuilder;
    }
    
    public AnchorPreviewBuilder hideDelay(int hideDelay) {
      this.hideDelay = hideDelay;
      return this;
    }

    public AnchorPreviewBuilder previewValueSelector(String previewValueSelector) {
      this.previewValueSelector = previewValueSelector;
      return this;
    }

    public AnchorPreviewBuilder showWhenToolbarIsVisible(boolean showWhenToolbarIsVisible) {
      this.showWhenToolbarIsVisible = showWhenToolbarIsVisible;
      return this;
    }
    
    public OptionsBuilder done() {
      return optionsBuilder;
    }

    AnchorPreview build() {
      return new AnchorPreview(this);
    }
    
  }

}
