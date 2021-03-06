package com.byteowls.vaadin.mediumeditor.demo.ui;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.byteowls.vaadin.mediumeditor.demo.ui.views.AddonView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.i18n.CustomTranslationEditorView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.i18n.EnglishEditorView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.i18n.GermanEditorView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.simple.AllButtonEditorView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.simple.NoConfigEditorView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.simple.WindowEditorView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.BeagleThemeView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.BootstrapThemeView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.DefaultThemeView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.FlatThemeView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.ManiThemeView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.RomanThemeView;
import com.byteowls.vaadin.mediumeditor.demo.ui.views.theme.TimThemeView;
import com.thedeanda.lorem.Lorem;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.ValoTheme;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.util.IllegalConfigurationException;

@Theme("mediumeditor")
@SpringUI
public class AddonDemoUI extends UI {

    private static final long serialVersionUID = -33887281222947647L;

    private static List<MenuItem> menuItems;
    static {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(EditorStructure.SIMPLE, "No config", NoConfigEditorView.class));
        menuItems.add(new MenuItem(EditorStructure.SIMPLE, "All buttons", AllButtonEditorView.class));
        menuItems.add(new MenuItem(EditorStructure.SIMPLE, "Window", WindowEditorView.class));
        menuItems.add(new MenuItem(EditorStructure.TRANSLATED, "English", EnglishEditorView.class));
        menuItems.add(new MenuItem(EditorStructure.TRANSLATED, "German", GermanEditorView.class));
        menuItems.add(new MenuItem(EditorStructure.TRANSLATED, "Mixed", CustomTranslationEditorView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Default", DefaultThemeView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Beagle", BeagleThemeView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Flat", FlatThemeView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Roman", RomanThemeView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Tim", TimThemeView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Mani", ManiThemeView.class));
        menuItems.add(new MenuItem(EditorStructure.THEMED, "Bootstrap", BootstrapThemeView.class));
    }

    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    private Environment env;
    @Autowired
    private EventBus.UIEventBus uiEventBus;


    private Label codeLabel;
    private Label previewLabel;
    private Panel editorComponent;

    private boolean readonly;
    private boolean htmlPreview;

    private Navigator navigator;

    @SuppressWarnings("serial")
    @Override
    protected void init(VaadinRequest request) {
        String title = env.getProperty("addon.title");
        getPage().setTitle(title);
        Responsive.makeResponsive(this);
        this.uiEventBus.subscribe(this);

        VerticalLayout vl = new VerticalLayout();
        vl.setMargin(false);
        vl.setSpacing(false);
        vl.setSizeFull();

        Label info = new Label("<strong>" + title + "</strong> "
                + "| Version: <strong>" + env.getProperty("addon.version") + "</strong> "
                + "| "+env.getProperty("addon.jslib.title")+": <strong>" + env.getProperty("addon.jslib.version") + "</strong> "
                + "| Vaadin: <strong>" + env.getProperty("addon.vaadin.version") + "</strong> "
                + "| <a href=\""+env.getProperty("addon.github")+"\">Check it out on Github</a>");
        info.setContentMode(ContentMode.HTML);

        CssLayout infoBar = new CssLayout(info);
        infoBar.setWidth(100, Unit.PERCENTAGE);
        infoBar.addStyleName("addon-info-bar");
        vl.addComponent(infoBar);

        HorizontalSplitPanel splitContentCode = new HorizontalSplitPanel();
        splitContentCode.setSizeFull();
        splitContentCode.setFirstComponent(buildContent());
        splitContentCode.setSecondComponent(buildCode());
        splitContentCode.setSplitPosition(50);

        HorizontalSplitPanel splitMenuContent = new HorizontalSplitPanel();
        splitMenuContent.setSizeFull();
        splitMenuContent.setFirstComponent(buildMenu());
        splitMenuContent.setSecondComponent(splitContentCode);
        splitMenuContent.setSplitPosition(15);
        vl.addComponent(splitMenuContent);
        vl.setExpandRatio(splitMenuContent, 1);

        navigator = new Navigator(this, editorComponent);
        navigator.addProvider(viewProvider);
        navigator.setErrorProvider(viewProvider);
        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                AddonView view = (AddonView) event.getNewView();
                String formattedSourceCode = getFormattedSourceCode(view.getSource());
                codeLabel.setValue(formattedSourceCode);
                previewLabel.setValue(null);
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
        setContent(vl);

        String fragment = Page.getCurrent().getUriFragment();
        if (fragment == null || fragment.equals("")) {
            String viewName = menuItems.get(0).getViewName();
            navigator.navigateTo(viewName);
        }
    }

    private Component buildContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setSpacing(false);
        editorComponent = new Panel();
        editorComponent.setSizeFull();
        editorComponent.addStyleName(ValoTheme.PANEL_BORDERLESS);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setMargin(false);
        toolbar.setSpacing(true);

        Button previewBtn = new Button(this.htmlPreview ? "Html Preview" : "Plain Preview");
        previewBtn.addClickListener(e -> {
            this.htmlPreview = !this.htmlPreview;
            previewBtn.setCaption(this.htmlPreview ? "Html Preview" : "Plain Preview");
            previewLabel.setContentMode(htmlPreview ? ContentMode.HTML : ContentMode.PREFORMATTED);
        });
        toolbar.addComponent(previewBtn);

        Button loremBtn = new Button("Generate Lorem Ispum");
        loremBtn.addClickListener(e -> {
            View currentView = navigator.getCurrentView();
            if (currentView instanceof AddonView) {
                AddonView addonView = (AddonView) currentView;
                addonView.setEditorText(Lorem.getHtmlParagraphs(2, 5));
            }
        });
        toolbar.addComponent(loremBtn);

        Button readonlyBtn = new Button(this.readonly ? "ReadOnly" : "Read/Write");
        readonlyBtn.addClickListener(e -> {
            this.readonly = !this.readonly;
            readonlyBtn.setCaption(this.readonly ? "ReadOnly" : "Read/Write");
            View currentView = navigator.getCurrentView();
            if (currentView instanceof AddonView) {
                AddonView addonView = (AddonView) currentView;
                addonView.setEditorReadOnly(this.readonly);
            }
        });
        toolbar.addComponent(readonlyBtn);


        layout.addComponent(toolbar);
        layout.setComponentAlignment(toolbar, Alignment.MIDDLE_CENTER);
        layout.addComponent(editorComponent);
        layout.setExpandRatio(editorComponent, 1);

        VerticalSplitPanel contentLayout = new VerticalSplitPanel();
        contentLayout.setFirstComponent(layout);
        contentLayout.setSecondComponent(buildPreview());
        contentLayout.setSplitPosition(75);

        return contentLayout;
    }

    private Component buildPreview() {
        previewLabel = new Label();
        previewLabel.setSizeFull();

        Panel panel = new Panel(previewLabel);
        panel.setCaption("Preview");
        panel.setSizeFull();
        panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        panel.addStyleName("addon-code");
        return panel;
    }

    private Component buildCode() {
        codeLabel = new Label();
        codeLabel.setContentMode(ContentMode.HTML);

        Panel codePanel = new Panel(codeLabel);
        codePanel.setSizeFull();
        codePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        codePanel.addStyleName("addon-code");
        return codePanel;
    }

    private Component buildMenu() {
        Panel menuPanel = new Panel();
        menuPanel.setSizeFull();
        menuPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        menuPanel.addStyleName("addon-menu");

        VerticalLayout vl = new VerticalLayout();
        vl.setWidth(100, Unit.PERCENTAGE);

        for (EditorStructure editorStructure : EditorStructure.values()) {
            List<MenuItem> children = new ArrayList<>();
            for (MenuItem i : menuItems) {
                if (i.getType() == editorStructure) {
                    children.add(i);
                }
            }

            Label section = new Label();
            section.addStyleName(ValoTheme.LABEL_SUCCESS);
            section.setSizeFull();
            section.setValue(editorStructure.toString());
            vl.addComponent(section);

            for (MenuItem menuItem : children) {
                Button b = new Button(menuItem.getLabel());
                b.setSizeFull();
                b.addClickListener(e -> {
                    getUI().getNavigator().navigateTo(menuItem.getViewName());
                    vl.forEach(c -> c.removeStyleName(ValoTheme.BUTTON_PRIMARY));
                    b.addStyleName(ValoTheme.BUTTON_PRIMARY);
                });
                vl.addComponent(b);
            }
        }
        menuPanel.setContent(vl);
        return menuPanel;
    }

    public String getFormattedSourceCode(String sourceCode) {
        if (sourceCode != null) {
            try {
                JavaSource source = new JavaSourceParser().parse(new StringReader(sourceCode));
                JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
                StringWriter writer = new StringWriter();
                JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault();
                options.setShowLineNumbers(false);
                options.setAddLineAnchors(false);
                converter.convert(source, options, writer);
                return writer.toString();
            } catch (IllegalConfigurationException | IOException exception) {

            }
        }
        return sourceCode;
    }

    @Override
    public void detach() {
        uiEventBus.unsubscribe(this);
        super.detach();
    }

    @EventBusListenerMethod
    public void setPreview(ValueChangeEvent event) {
        previewLabel.setValue(event.getValue());
    }

}
