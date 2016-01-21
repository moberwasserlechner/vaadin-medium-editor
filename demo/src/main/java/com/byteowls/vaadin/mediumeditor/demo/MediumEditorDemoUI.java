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
    MediumEditor me1 = new MediumEditor();
    me1.setSizeFull();
    me1.setLoggingEnabled(true);
    me1.setContent(Lorem.getHtmlParagraphs(3, 3));
    me1.addValueChangeListener(value -> {
      preview.setValue(value);
    });
    me1.configure(
        me1.options()
        .toolbar()
        .buttons(
            BuildInButton.BOLD, 
            BuildInButton.ITALIC, 
            BuildInButton.UNDERLINE, 
            BuildInButton.ORDEREDLIST,
            BuildInButton.UNORDEREDLIST, 
            BuildInButton.QUOTE,
            BuildInButton.REMOVE_FORMAT,
            BuildInButton.ANCHOR
            ).and()
        .placeholder()
          .text("Hallo").and()
//        .autoLink(true)
        .imageDragging(false)
        );


    vl.addComponent(me1);
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

    vl.setExpandRatio(me1, 1);
    vl.setExpandRatio(preview, 1);
    vl.setExpandRatio(btnHl, 0.2f);

    setContent(vl);
  }

}
