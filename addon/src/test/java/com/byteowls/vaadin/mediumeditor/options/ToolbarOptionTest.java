package com.byteowls.vaadin.mediumeditor.options;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import com.byteowls.vaadin.mediumeditor.MediumEditor;

import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class ToolbarOptionTest {
  
  @Test
  public void testAllButtons() {
    MediumEditor e2 = new MediumEditor();

    String exception = null;
    try {
      e2.options()
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
    .button(Buttons.BOLD)
    .done().build();
    
    for (ToolbarButton b : options.toolbar.buttons) {
      Assert.assertEquals("Fett", b.aria);
    }
  }
  
  @Test
  public void testNonExistingPlatformFallbackTranslationJson() {
    MediumEditor e2 = new MediumEditor();
    JsonValue options = e2.options()
    .locale(Locale.CHINESE)
    .useDefaultLocaleFallback(true) // Locale.getLocale()
    .fontawesomeButtonLabels()
    .toolbar()
    .button(Buttons.BOLD)
    .done().buildJson();
    
    JsonObject obj = (JsonObject) options;
    
    JsonObject toolbar = obj.getObject("toolbar");
    JsonArray buttons = toolbar.getArray("buttons");
    for (int i = 0; i < buttons.length(); i++) {
      JsonObject b = buttons.getObject(i);
      Assert.assertEquals("Fett", b.getString("aria"));
    }
  }
  
  @Test
  public void testGermanTranslation() {
    MediumEditor e2 = new MediumEditor();
    Options options = e2.options()
    .locale(Locale.GERMAN)
    .fontawesomeButtonLabels()
    .toolbar()
    .button(Buttons.BOLD)
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
    .button(Buttons.BOLD)
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
    .button(Buttons.BOLD, "0000")
    .done().build();
    
    for (ToolbarButton b : options.toolbar.buttons) {
      Assert.assertEquals("0000", b.aria);
    }
  }
  
}
