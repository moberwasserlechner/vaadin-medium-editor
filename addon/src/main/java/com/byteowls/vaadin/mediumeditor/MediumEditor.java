package com.byteowls.vaadin.mediumeditor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options;
import com.byteowls.vaadin.mediumeditor.options.MediumEditorTheme;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

@StyleSheet({"vaadin://mediumeditor/css/medium-editor.min.css", "vaadin://mediumeditor/css/addon.min.css"})
@JavaScript({ "vaadin://mediumeditor/js/medium-editor.min.js", "vaadin://mediumeditor/js/medium-editor-connector.js" })
public class MediumEditor extends AbstractJavaScriptComponent {

    private static final long serialVersionUID = -5253978488866030676L;

    public interface BlurListener extends Serializable {
        void valueChange(String value);
    }
    private List<MediumEditor.BlurListener> blurListeners = new ArrayList<>();

    public MediumEditor() {
        init();
    }

    /**
     * Configure the MediumEditor.
     * @param builder the option builder. Start by using {@link MediumEditor#options()}.
     */
    public void configure(Options.OptionsBuilder builder) {
        if (builder != null) {
            getState().optionsJson = builder.buildJson();
        }
    }

    /**
     * Returns the starting point for configuring the editor's options.
     * @return the builder for configuring the editor's options
     */
    public Options.OptionsBuilder options() {
        return Options.builder();
    }

    /**
     * Set the content of editor
     * @param content the editor content / value.
     */
    public void setContent(String content) {
        getState().content = content;
    }

    /**
     * Add a listener to handle the changed value from the editor.
     * @param listener a simple blurlistener retrieving just the value
     */
    public void addBlurListener(MediumEditor.BlurListener listener) {
        this.blurListeners.add(listener);
    }

    /**
     * @return True if the connector's logs defined messages to "console.log" else logging is disabled.
     */
    public boolean isJsLoggingEnabled() {
        return getState().loggingEnabled;
    }

    /**
     * Enable or disables the connector's logging to "console.log"
     * @param jsLoggingEnabled If true the connector script will log defined messages to "console.log". Defaults to false. 
     */
    public void setJsLoggingEnabled(boolean jsLoggingEnabled) {
        getState().loggingEnabled = jsLoggingEnabled;
    }

    /**
     * Enable or disables the inline editing.
     * If readOnly is toggled on a initialised editor the editor's content will be reset to the server side content.
     * @param readOnly If true no editing is possible. Defaults to false.
     */
    public void setReadOnly(boolean readOnly) {
        getState().readOnly = readOnly;
    }

    /* (non-Javadoc)
     * @see com.vaadin.ui.AbstractComponent#isReadOnly()
     */
    public boolean isReadOnly() {
        return getState().readOnly;
    }

    /**
     * Enables or disables the border around the component when it gets the focus.
     * @param focusOutlineEnabled True if a border around the component should be shown as soon as it gets the focus. If you don't like the border set it to false. Defaults to true.
     */
    public void setFocusOutlineEnabled(boolean focusOutlineEnabled) {
        getState().focusOutlineEnabled = focusOutlineEnabled;
    }

    /**
     * If true the focus outline border is enabled else disabled.
     * @return True if the focus outline border is enabled else false.
     */
    public boolean isFocusOutlineEnabled() {
        return getState().focusOutlineEnabled;
    }

    /**
     * Sets the theme of the medium editor. The theme is global and is therefore assigned to all editors in the view.
     * @param theme the medium editor theme.
     */
    public void setTheme(MediumEditorTheme theme) {
        if (theme == null) {
            theme = MediumEditorTheme.DEFAULT;
        }
        getState().theme = theme;
    }

    @SuppressWarnings("serial")
    private void init() {
        setSizeFull();
        // this function can be called in medium-editor-connector e.g. self.onValueChange(stringValue)
        addFunction("onValueChange", new JavaScriptFunction() {
            @Override
            public void call(JsonArray args) {
                String value = args.getString(0);
                if (value != null && (value.isEmpty() || value.equals("<p><br></p>"))) {
                    value = null;
                }
                for (BlurListener l : blurListeners) {
                    l.valueChange(value);
                }
            }
        });
    }

    @Override
    protected MediumEditorState getState() {
        return (MediumEditorState) super.getState();
    }

}
