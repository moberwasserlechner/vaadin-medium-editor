package com.byteowls.vaadin.mediumeditor.demo.ui.views.simple;

import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.AbstractAddonView;
import com.byteowls.vaadin.mediumeditor.options.Buttons;
import com.thedeanda.lorem.Lorem;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class NoConfigEditorView extends AbstractAddonView {

    private static final long serialVersionUID = 4894923343920891837L;
    
    @Override
    public Component getAddonComponent() {
        
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
        return editor; 
    }

}
