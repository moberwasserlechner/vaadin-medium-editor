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

  @Override
  protected void init(VaadinRequest request) {

    VerticalLayout vl = new VerticalLayout();
    vl.setMargin(true);
    vl.setSizeFull();
    vl.addStyleName(ValoTheme.LAYOUT_WELL);

    preview = new Label();
    MediumEditor editor = new MediumEditor();
    editor.setSizeFull();
    editor.setFocusOutlineEnabled(false);
    editor.setJsLoggingEnabled(true);
    editor.setContent(Lorem.getHtmlParagraphs(3, 3));
    editor.addBlurListener(value -> {
      preview.setValue(value);
    });
    editor.configure(
        editor.options()
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


    vl.addComponent(editor);
    vl.addComponent(preview);

    Button btnFormatted = new Button("Preview formatted!");
    btnFormatted.addClickListener(e -> {
      preview.setContentMode(ContentMode.HTML);
    });

    Button btnPlain = new Button("Preview html source!");
    btnPlain.addClickListener(e -> {
      preview.setContentMode(ContentMode.TEXT);
    });

    HorizontalLayout btnHl = new HorizontalLayout(btnFormatted, btnPlain);
    btnHl.setSpacing(true);
    vl.addComponent(btnHl);
    vl.setComponentAlignment(btnHl, Alignment.BOTTOM_CENTER);

    vl.setExpandRatio(editor, 1);
    vl.setExpandRatio(preview, 1);
    vl.setExpandRatio(btnHl, 0.2f);

    setContent(vl);
  }

}
