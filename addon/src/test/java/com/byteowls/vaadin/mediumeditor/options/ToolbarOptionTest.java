package com.byteowls.vaadin.mediumeditor.options;

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

}
