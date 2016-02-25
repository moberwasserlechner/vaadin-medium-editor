package com.byteowls.vaadin.mediumeditor.demo;

import java.util.Locale;

import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.options.Buttons;
import com.thedeanda.lorem.Lorem;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
    vl.setSpacing(true);
    vl.setSizeFull();

    preview = new Label();
    preview.setContentMode(ContentMode.HTML);
    Panel previewPanel = new Panel(preview);
    previewPanel.setSizeFull();
    previewPanel.setCaption("Preview");
    
    MediumEditor e1 = new MediumEditor();
    e1.setSizeFull();
    e1.setFocusOutlineEnabled(false);
    e1.setJsLoggingEnabled(true);
    e1.setContent(Lorem.getHtmlParagraphs(3, 5));
    e1.addBlurListener(value -> {
      preview.setValue(value);
    });
    e1.configure(
        e1.options()
        .fontawesomeButtonLabels()
        .toolbar()
          .buttons(Buttons.BOLD, Buttons.ITALIC, Buttons.H1, Buttons.JUSTIFY_CENTER)
          // numberic button translations ;)
          .buttonTranslations("1", "2", "3", "4")
          .done()
        .autoLink(true)
        .imageDragging(false)
        .done()
        );
    
    Panel p1 = new Panel(e1);
    p1.setCaption("Simple editor");
    p1.setSizeFull();
    
    MediumEditor e2 = new MediumEditor();
    e2.setSizeFull();
    e2.setContent(Lorem.getHtmlParagraphs(2, 5));
    e2.addBlurListener(value -> {
      preview.setValue(value);
    });
    e2.configure(
        e2.options()
        .locale(Locale.GERMAN)
        .fontawesomeButtonLabels()
        .toolbar()
          .allButtons()
          .done()
        .placeholder()
          .text("Input prompt")
          .done()
        .imageDragging(false)
          .done()
        );
    
    Panel p2 = new Panel(e2);
    p2.setCaption("Editor with all buildin buttons");
    p2.setSizeFull();
    

    HorizontalLayout editorHl = new HorizontalLayout(p1, p2);
    editorHl.setSpacing(true);
    editorHl.setSizeFull();

    vl.addComponent(editorHl);
    vl.addComponent(previewPanel);

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
    vl.setExpandRatio(previewPanel, 1);
    vl.setExpandRatio(btnHl, 0.2f);

    setContent(vl);
  }

}
