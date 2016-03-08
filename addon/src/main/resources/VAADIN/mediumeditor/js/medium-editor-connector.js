window.com_byteowls_vaadin_mediumeditor_MediumEditor = function() {
  // see the javadoc of com.vaadin.ui.AbstractJavaScriptComponent for all functions on this.
  var THEME_ID = "medium-editor-theme-id";
  var mediumEditor;
  var mediumThemeLink;
  var e = this.getElement();
  // Please note that in JavaScript, this is not necessarily defined inside callback functions and it might therefore be necessary to assign the reference to a separate variable
  var self = this;
  var loggingEnabled = false;
  var focusOutlineEnabled = true;
  var readOnly = false;

  // called every time MediumEditorState is changed
  this.onStateChange = function() {
    var state = this.getState();
    loggingEnabled = state.loggingEnabled;
    focusOutlineEnabled = state.focusOutlineEnabled;
    readOnly = state.readOnly;

    // #1 theme
    if (typeof mediumThemeLink === 'undefined') {
      // check if a theme css is already loaded
      mediumThemeLink = document.getElementById(THEME_ID);
      if (mediumThemeLink == null) {
        mediumThemeLink = document.createElement("link");
        mediumThemeLink.setAttribute("rel", "stylesheet");
        mediumThemeLink.setAttribute("type", "text/css");
        mediumThemeLink.setAttribute("id", THEME_ID);
        document.getElementsByTagName("head")[0].appendChild(mediumThemeLink);
      }
    }
    var url = this.translateVaadinUri("vaadin://mediumeditor/css/"+state.theme.toLowerCase()+".min.css");
    mediumThemeLink.setAttribute("href", url);

    if (loggingEnabled) {
      console.log("medium-editor: setting value to\n" + state.content);
    }

    if (!focusOutlineEnabled) {
      e.style.outline = "none";
    }

    if (typeof mediumEditor === 'undefined') {
      if (loggingEnabled) {
        console.log("medium-editor: init editor");
      }

      // #12 enable text selection on contenteditable for Safari
      if (loggingEnabled) {
        console.log("medium-editor: enable user-select on contenteditable div because it might be deactivated by valo");
      }
      e.style.webkitUserSelect = "text";
      e.style.userSelect = "text";
      e.style.MozUserSelect = "text";
      e.style.msUserSelect = "text"; // For IE10+
      
      // ensure that pressing return or enter creates a new line
      e.addEventListener("keydown", function(event) {
        if (event.which == 13 || event.keyCode == 13) { 
          event.stopPropagation();
        }
      });

      e.addEventListener("blur", function() {
        if (!readOnly) {
          var val = e.innerHTML;
          if (loggingEnabled) {
            console.log("medium-editor: value on blur is\n" + val);
          }        
          self.onValueChange(val);
        }
      });

      if (loggingEnabled) {
        console.log("medium-editor: options are\n", JSON.stringify(state.optionsJson, null, 2));
      }
      mediumEditor = new MediumEditor(e, state.optionsJson);
    }

    // #18 placeholder does not disappear if not using mediumEditor's setContent()
    mediumEditor.setContent(state.content || "");

    if (readOnly) {
      if (loggingEnabled) {
        console.log("medium-editor: destroying editor because readOnly is enabled!");
      }
      mediumEditor.destroy();
    } else {
      mediumEditor.setup();
    }
  };

}
