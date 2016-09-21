package com.byteowls.vaadin.mediumeditor.demo.ui.views.i18n;

import java.util.Locale;

import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.AbstractAddonView;
import com.byteowls.vaadin.mediumeditor.options.MediumEditorTheme;
import com.thedeanda.lorem.Lorem;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class GermanEditorView extends AbstractAddonView {

    private static final long serialVersionUID = 4894923343920891837L;
    
    @Override
    public Component getAddonComponent() {
        
        MediumEditor editor = new MediumEditor();
        editor.setSizeFull();
        editor.setTheme(MediumEditorTheme.BOOTSTRAP);
        editor.setFocusOutlineEnabled(false);
        editor.setJsLoggingEnabled(true);
        editor.setContent(Lorem.getHtmlParagraphs(3, 5));
        editor.configure(
            editor.options()
                // configure locale fallback to UI.getLocale()
                .locale(Locale.GERMAN)
                .toolbar()
                    .allButtons()
                    .done()
                .autoLink(true)
                .imageDragging(false)
                .done()
            );
        editors.add(editor);
        return editor; 
    }

}
