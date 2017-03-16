package com.byteowls.vaadin.mediumeditor.demo.ui.views.simple;


import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.AbstractAddonView;
import com.byteowls.vaadin.mediumeditor.options.Buttons;
import com.thedeanda.lorem.Lorem;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView
public class WindowEditorView extends AbstractAddonView {

    private static final long serialVersionUID = 4894923343920891837L;

    @Override
    public Component getAddonComponent() {
        Window w = new Window("Medium Editor in window");
        w.setWidth(500, Unit.PIXELS);
        w.setHeight(400, Unit.PIXELS);
        
        
        Panel p = new Panel();
        p.addStyleName(ValoTheme.PANEL_WELL);
        p.setSizeFull();

        MediumEditor editor = new MediumEditor();
        editor.setSizeFull();
        editor.setFocusOutlineEnabled(false);
        editor.setJsLoggingEnabled(true);
        editor.setContent(Lorem.getHtmlParagraphs(3, 5));
        editor.configure(
                editor.options()
                .toolbar()
                .buttons(Buttons.BOLD, Buttons.ITALIC, 
                        Buttons.JUSTIFY_CENTER, 
                        Buttons.ANCHOR)
                .done()
                .autoLink(true)
                .imageDragging(false)
                .done()
                );
        editors.add(editor);
        p.setContent(editor);
        w.setContent(p);

        Button b = new Button("Open Window");
        b.addClickListener(e -> {
            w.setPosition(e.getClientX(), e.getClientY());
            UI.getCurrent().addWindow(w);
        });


        return b; 
    }

}
