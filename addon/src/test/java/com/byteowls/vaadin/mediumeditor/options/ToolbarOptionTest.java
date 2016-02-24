package com.byteowls.vaadin.mediumeditor.options;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import com.byteowls.vaadin.mediumeditor.MediumEditor;

public class ToolbarOptionTest {
  
  @Test
  public void testAllButtons() {
    MediumEditor e2 = new MediumEditor();

    String exception = null;
    try {
      e2.options()
      .fontawesomeButtonLabels()
      .toolbar()
        .allButtons()
        .done();
    } catch (Exception e) {
      exception = e.getMessage();
    }
    Assert.assertNull(exception);
  }
  
  /**
   * ATTENTION: this test is very specific to the platforms locale the test runing at.
   * 
   * In my case I was testing on Win7 and Locale.GERMAN
   * 
   * Maybe I remove this test because its to specific.
   */
  @Test
  public void testNonExistingPlatformFallbackTranslation() {
    MediumEditor e2 = new MediumEditor();
    Options options = e2.options()
    .locale(Locale.CHINESE)
    .useDefaultLocaleFallback(true) // Locale.getLocale()
    .fontawesomeButtonLabels()
    .toolbar()
    .button(BuildInButton.BOLD)
    .done().build();
    
    for (ToolbarButton b : options.toolbar.buttons) {
      Assert.assertEquals("Fett", b.aria);
    }
  }
  
  @Test
  public void testGermanTranslation() {
    MediumEditor e2 = new MediumEditor();
    Options options = e2.options()
    .locale(Locale.GERMAN)
    .fontawesomeButtonLabels()
    .toolbar()
    .button(BuildInButton.BOLD)
    .done().build();
    
    for (ToolbarButton b : options.toolbar.buttons) {
      Assert.assertEquals("Fett", b.aria);
    }
  }
  
  @Test
  public void testNonExistingTranslation() {
    MediumEditor e2 = new MediumEditor();
    Options options = e2.options()
    .locale(Locale.CHINESE)
    .toolbar()
    .button(BuildInButton.BOLD)
    .done().build();
    
    for (ToolbarButton b : options.toolbar.buttons) {
      Assert.assertEquals("Bold", b.aria);
    }
  }
  
  @Test
  public void testCustomTranslation() {
    MediumEditor e2 = new MediumEditor();
    Options options = e2.options()
    .fontawesomeButtonLabels()
    .toolbar()
    .button(BuildInButton.BOLD, "0000")
    .done().build();
    
    for (ToolbarButton b : options.toolbar.buttons) {
      Assert.assertEquals("0000", b.aria);
    }
  }
  
}
