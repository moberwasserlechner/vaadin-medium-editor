window.com_byteowls_vaadin_medium_editor_MediumEditor = function() {
  // see the javadoc of com.vaadin.ui.AbstractJavaScriptComponent for all functions on this.
  var mediumEditor;
  var e = this.getElement();
  // Please note that in JavaScript, this is not necessarily defined inside callback functions and it might therefore be necessary to assign the reference to a separate variable
  var self = this;
  var debug = false;
  
  // called every time MediumEditorState is changed
  this.onStateChange = function() {
    debug = state.debug;
    if (!debug) {
      console.log("medium-editor: onStateChange");
    }
    var state = this.getState();
    if (state.value != null) {
      if (!debug) {
        console.log("medium-editor: assign value " + state.value);
      }
      e.innerHTML = state.value;
    }
    
    if (typeof mediumEditor === 'undefined') {
      if (!debug) {
        console.log("medium-editor: init editor");
      }
      
      e.addEventListener("keydown", function(event) {
        if (event.which == 13 || event.keyCode == 13) {
          event.stopPropagation();
        }
      });
      
      mediumEditor = new MediumEditor(e, state.options);
      mediumEditor.subscribe('editableInput', function (event, editable) {
        var val = e.innerHTML;
        if (!debug) {
          console.log("medium-editor: value changed to\n" + val);
        }
        self.onValueChange(val);
      });
    }
  };
  
}
