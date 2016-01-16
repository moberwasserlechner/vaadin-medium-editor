package com.byteowls.vaadin.medium.editor.demo;

import com.byteowls.vaadin.medium.editor.MediumEditor;
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
    MediumEditor me1 = new MediumEditor("Test Medium Editor");
    me1.setSizeFull();
    me1.setValue(Lorem.getParagraphs(2, 2));
    me1.addValueChangeListener(e -> {
      preview.setValue(me1.getValue());
    });
    vl.addComponent(me1);
    vl.addComponent(preview);


    Button btnFormatted = new Button("Get formatted!");
    btnFormatted.addClickListener(e -> {
      preview.setContentMode(ContentMode.HTML);
//      preview.setValue(me1.getValue());
    });

    Button btnPlain = new Button("Get html source!");
    btnPlain.addClickListener(e -> {
      preview.setContentMode(ContentMode.TEXT);
//      preview.setValue(me1.getValue());
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
