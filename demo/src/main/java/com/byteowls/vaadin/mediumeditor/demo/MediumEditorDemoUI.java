package com.byteowls.vaadin.mediumeditor.demo;

import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.options.BuildInButton;
import com.thedeanda.lorem.Lorem;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mediumeditor")
@Widgetset("MediumEditorWidgetset")
public class MediumEditorDemoUI extends UI {

  private static final long serialVersionUID = -33887281222947647L;

  private Label preview = null;

  private Button btnFormatted;

  private Button btnPlain;

  @Override
  protected void init(VaadinRequest request) {

    VerticalLayout vl = new VerticalLayout();
    vl.setMargin(true);
    vl.setSizeFull();
    vl.addStyleName(ValoTheme.LAYOUT_WELL);

    preview = new Label();
    preview.setContentMode(ContentMode.HTML);
    
    MediumEditor e1 = new MediumEditor();
    e1.setSizeFull();
    e1.setFocusOutlineEnabled(false);
    e1.setJsLoggingEnabled(true);
    e1.setContent(Lorem.getHtmlParagraphs(1, 3));
    e1.addBlurListener(value -> {
      preview.setValue(value);
    });
    e1.configure(
        e1.options()
        .fontawesomeButtonLabels()
        .toolbar()
          .buttons(BuildInButton.BOLD, BuildInButton.ITALIC, BuildInButton.H1, BuildInButton.JUSTIFY_CENTER)
          // german button translations
          .buttonTranslations("fett", "kursiv", "Ueberschrift1", "zentriert")
          .done()
        .placeholder()
          .text("Input prompt")
          .done()
        .autoLink(true)
        .imageDragging(false)
        .done()
        );
    
    MediumEditor e2 = new MediumEditor();
    e2.setSizeFull();
    e2.setContent(Lorem.getHtmlParagraphs(1, 2));
    e2.addBlurListener(value -> {
      preview.setValue(value);
    });
    e2.configure(
        e2.options()
        .fontawesomeButtonLabels()
        .toolbar()
          .allButtons()
          .done()
        .imageDragging(false)
        .done()
        );

    HorizontalLayout editorHl = new HorizontalLayout(e1, e2);
    editorHl.addStyleName(ValoTheme.LAYOUT_CARD);
    editorHl.setSpacing(true);
    editorHl.setSizeFull();

    vl.addComponent(editorHl);
    vl.addComponent(preview);

    btnFormatted = new Button("Preview formatted!");
    btnFormatted.addStyleName(ValoTheme.BUTTON_FRIENDLY);
    btnFormatted.addClickListener(e -> {
      btnFormatted.addStyleName(ValoTheme.BUTTON_FRIENDLY);
      btnPlain.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
      preview.setContentMode(ContentMode.HTML);
    });

    btnPlain = new Button("Preview html source!");
    btnPlain.addClickListener(e -> {
      btnPlain.addStyleName(ValoTheme.BUTTON_FRIENDLY);
      btnFormatted.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
      preview.setContentMode(ContentMode.TEXT);
    });
    
    Button readOnly = new Button("Toggle e2 ReadOnly");
    readOnly.addClickListener(e -> {
      e2.setReadOnly(!e2.isReadOnly());
    });

    HorizontalLayout btnHl = new HorizontalLayout(btnFormatted, btnPlain, readOnly);
    btnHl.setSpacing(true);
    vl.addComponent(btnHl);
    vl.setComponentAlignment(btnHl, Alignment.BOTTOM_CENTER);

    vl.setExpandRatio(editorHl, 1);
    vl.setExpandRatio(preview, 1);
    vl.setExpandRatio(btnHl, 0.2f);

    setContent(vl);
  }

}
