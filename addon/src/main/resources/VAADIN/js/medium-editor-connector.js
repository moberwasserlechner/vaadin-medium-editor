window.com_byteowls_vaadin_medium_editor_MediumEditor = function() {
  // see the javadoc of com.vaadin.ui.AbstractJavaScriptComponent for all functions on this.
  var mediumEditor;
  var e = this.getElement();
  var textarea;
  // Please note that in JavaScript, this is not necessarily defined inside callback functions and it might therefore be necessary to assign the reference to a separate variable
  var self = this;
  
  // called every time MediumEditorState is changed
  this.onStateChange = function() {
    console.log("medium-editor: onStateChange");
    var state = this.getState();
    // init textarea only once
    if (typeof textarea === 'undefined') {
      console.log("medium-editor: init textarea");
      textarea = document.createElement("textarea");
      textarea.addEventListener("keydown", function(event) {
        if (event.which == 13 || event.keyCode == 13) {
          event.preventDefault();
        }
      });
      e.appendChild(textarea);
    }
    
    if (state.value != null) {
      console.log("medium-editor: set value" + state.value);
      textarea.value = state.value;
    }
    
    if (typeof mediumEditor === 'undefined') {
      console.log("medium-editor: init editor");
      mediumEditor = new MediumEditor(textarea, state.options);
      mediumEditor.subscribe('editableInput', function (event, editable) {
        //console.log("medium-editor: observing any changes");
        self.onValueChange(textarea.value);
      });
    }
  };
  
}
