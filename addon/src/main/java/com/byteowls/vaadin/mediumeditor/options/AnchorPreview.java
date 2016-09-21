package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public class AnchorPreview implements Serializable {

    private static final long serialVersionUID = -564862914858140461L;

    public Integer hideDelay = null;
    public String previewValueSelector;
    public Boolean showWhenToolbarIsVisible;

    private AnchorPreview(AnchorPreviewBuilder builder) {
        hideDelay = builder.hideDelay;
        previewValueSelector = builder.previewValueSelector;
        showWhenToolbarIsVisible = builder.showWhenToolbarIsVisible;
    }

    public static AnchorPreviewBuilder builder(OptionsBuilder optionsBuilder) {
        return new AnchorPreviewBuilder(optionsBuilder);
    }

    public static class AnchorPreviewBuilder extends AbstractBuilder<AnchorPreview> {
        // reference to parent
        private OptionsBuilder optionsBuilder;

        private Integer hideDelay = null;
        private String previewValueSelector;
        private Boolean showOnEmptyLinks;
        private Boolean showWhenToolbarIsVisible;

        public AnchorPreviewBuilder(OptionsBuilder optionsBuilder) {
            this.optionsBuilder = optionsBuilder;
        }

        /**
         * Time in milliseconds to show the anchor tag preview after the mouse has left the anchor tag. Default: 500
         */
        public AnchorPreviewBuilder hideDelay(int hideDelay) {
            this.hideDelay = hideDelay;
            return this;
        }

        /**
         * The default selector to locate where to put the activeAnchor value in the preview. You should only need to override this if you've modified the way in which the anchor-preview extension renders.
         */
        public AnchorPreviewBuilder previewValueSelector(String previewValueSelector) {
            this.previewValueSelector = previewValueSelector;
            return this;
        }

        /**
         * Determines whether the anchor tag preview shows up on link with href as "" or "#something". 
         * You should set this value to false if you do not want the preview to show up in such use cases. 
         * Default: true
         */
        public AnchorPreviewBuilder showOnEmptyLinks(boolean showOnEmptyLinks) {
            this.showOnEmptyLinks = showOnEmptyLinks;
            return this;
        }

        /**
         * Determines whether the anchor tag preview shows up when the toolbar is visible. 
         * You should set this value to true if the static option for the toolbar is true and you want the preview to show at the same time.
         * Default: false
         */
        public AnchorPreviewBuilder showWhenToolbarIsVisible(boolean showWhenToolbarIsVisible) {
            this.showWhenToolbarIsVisible = showWhenToolbarIsVisible;
            return this;
        }

        public OptionsBuilder done() {
            return optionsBuilder;
        }

        @Override
        public AnchorPreview build() {
            return new AnchorPreview(this);
        }

        @Override
        public JsonValue buildJson() {
            JsonObject map = Json.createObject();
            putNotNull(map, "hideDelay", hideDelay);
            putNotNull(map, "previewValueSelector", previewValueSelector);
            putNotNull(map, "showOnEmptyLinks", showOnEmptyLinks);
            putNotNull(map, "showWhenToolbarIsVisible", showWhenToolbarIsVisible);
            return map;
        }

    }

}
