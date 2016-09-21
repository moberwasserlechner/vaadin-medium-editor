package com.byteowls.vaadin.mediumeditor.demo.ui.views.i18n;

import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.AbstractAddonView;
import com.byteowls.vaadin.mediumeditor.options.Buttons;
import com.byteowls.vaadin.mediumeditor.options.MediumEditorTheme;
import com.thedeanda.lorem.Lorem;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class CustomTranslationEditorView extends AbstractAddonView {

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
                .toolbar()
                    .buttons(Buttons.BOLD, 
                            Buttons.ITALIC, 
                            Buttons.STRIKETHROUGH, 
                            Buttons.REMOVE_FORMAT)
                    // Mix translations
                    .buttonTranslations("Fett (german)", 
                            "斜體 (chinese trad)", 
                            "yliviivattu (finnish)", 
                            "Remove format")
                    .done()
                .autoLink(true)
                .imageDragging(false)
                .done()
            );
        editors.add(editor);
        return editor; 
    }

}
