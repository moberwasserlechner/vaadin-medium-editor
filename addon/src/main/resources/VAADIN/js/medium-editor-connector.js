window.com_byteowls_vaadin_medium_editor_MediumEditor = function() {
  // see the javadoc of com.vaadin.ui.AbstractJavaScriptComponent for all functions on this.
  var mediumEditor;
  var e = this.getElement();
  // Please note that in JavaScript, this is not necessarily defined inside callback functions and it might therefore be necessary to assign the reference to a separate variable
  var self = this;
  
  // called every time MediumEditorState is changed
  this.onStateChange = function() {
    //console.log("medium-editor: onStateChange");
    var state = this.getState();
    
    if (state.value != null) {
      //console.log("medium-editor: set value" + state.value);
      e.innerHTML = state.value;
    }
    
    if (typeof mediumEditor === 'undefined') {
      //console.log("medium-editor: init editor" + state.options);
      mediumEditor = new MediumEditor(e, state.options);
      mediumEditor.subscribe('editableInput', function (event, editable) {
        //var editorValue = e.innerHTML;
        //console.log("medium-editor: observing any changes");
        self.onValueChange(e.innerHTML);
      });
    }
  };
  
}
