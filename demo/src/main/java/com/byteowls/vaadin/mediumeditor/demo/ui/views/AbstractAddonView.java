package com.byteowls.vaadin.mediumeditor.demo.ui.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.vaadin.spring.events.EventBus.UIEventBus;

import com.byteowls.vaadin.mediumeditor.MediumEditor;
import com.byteowls.vaadin.mediumeditor.demo.ui.ValueChangeEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractAddonView extends VerticalLayout implements AddonView {

    private static final long serialVersionUID = 2262543995706089766L;

    @Autowired
    private UIEventBus eventBus;

    protected List<MediumEditor> editors = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        setSizeFull();
        setMargin(true);

        Component layout = getAddonComponent();
        for (MediumEditor editor : editors) {
            editor.addBlurListener(value -> {
                this.eventBus.publish(this, new ValueChangeEvent(value));
            });
        }
        layout.setWidth(100, Unit.PERCENTAGE);
        addComponent(layout);
        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    @Override
    public String getSource() {
        // thanks to https://github.com/johndevs/dragdroplayouts/blob/master/demo/src/main/java/fi/jasoft/dragdroplayouts/demo/DemoView.java#L41
        String path = getClass().getCanonicalName().replaceAll("\\.", "/") + ".java";
        InputStream in = null;
        try {
            if ("dev".equals(System.getProperty("profile"))) {
                File f = new File(System.getProperty("user.dir") + "/src/main/java/"+path);
                in = new FileInputStream(f);
            } else {
                in = getClass().getClassLoader().getResourceAsStream(path);
            }

            if (in != null) {

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    boolean inCodeBlock = false;

                    StringBuilder codelines = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        if (line.contains("public Component getAddonComponent(")) {
                            inCodeBlock = true;
                        } else if (line.contains("return ")) {
                            inCodeBlock = false;
                        } else if (inCodeBlock) {
                            codelines.append(line);
                            codelines.append("\n");
                        } 
                        line = reader.readLine();
                    }
                    return codelines.toString();
                } catch (Exception ignore) {}
            }
        } catch (Exception ignore) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) { }
        }
        return null;
    }

    @Override
    public void setEditorText(String text) {
        if (this.editors != null) {
            for (MediumEditor m : this.editors) {
                m.setContent(text);
            }
        }
    }

    @Override
    public void setEditorReadOnly(boolean readOnly) {
        if (this.editors != null) {
            for (MediumEditor m : this.editors) {
                m.setReadOnly(readOnly);
            }
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

    @Override
    public String getViewName() {
        Class<?> realBeanClass = ClassUtils.getUserClass(getClass());
        return realBeanClass.getSimpleName();
    }



}
