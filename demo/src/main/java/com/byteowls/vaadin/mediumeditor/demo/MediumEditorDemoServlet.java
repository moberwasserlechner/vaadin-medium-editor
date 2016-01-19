package com.byteowls.vaadin.mediumeditor.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(ui = MediumEditorDemoUI.class, productionMode = false)
public class MediumEditorDemoServlet extends VaadinServlet {

  private static final long serialVersionUID = -3106247387634993220L;
  
}
