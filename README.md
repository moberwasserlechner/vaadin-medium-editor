# Vaadin Medium Editor

Vaadin 7 wrapper for the medium editor javascript library. https://github.com/yabwe/medium-editor

## Features

* MediumEditor component
* MediumEditorField for simple usage in `com.vaadin.data.fieldgroup.BeanFieldGroup<T>` or `com.vaadin.data.fieldgroup.FieldGroup` 
* Configure the editor's options with a fluent api
* Usage of Vaadin's FontAwesome integration, therefore no addional css file must be included. 
* Localization of build in buttons becomes possible. Although it must be done by yourself. Medium-Editor does not support other languages out of the box.

## Installation

### Download

[ ![Download](https://api.bintray.com/packages/moberwasserlechner/maven/vaadin-medium-editor/images/download.svg) ](https://bintray.com/moberwasserlechner/maven/vaadin-medium-editor/_latestVersion)

### Maven

Repository

    <repositories>
      <!-- ... other repository elements ... -->
      <repository>
        <snapshots>
          <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
      </repository>
    </repositories>
    
Dependency

    <dependencies>
      <!-- ... other dependency elements ... -->
      <dependency>
        <groupId>com.byteowls</groupId>
        <artifactId>vaadin-medium-editor</artifactId>
        <version>1.0.0</version>
      </dependency>
    </dependencies>


### Gradle

Repository

    repositories {
      jcenter()
    }
    // or 
    repositories {
      maven {
        url  "http://jcenter.bintray.com" 
      }
    }
     
Dependency

    dependencies {
      compile ("com.byteowls:vaadin-medium-editor:1.0.0")
    }

## Prerequisite

### Addon
* JDK 7 or higher
* Vaadin 7.4 or higher

### Demo
* JDK 8 (because of Lambdas)
* Vaadin 7.4 or higher

## Usage

### MediumEditor
  
    MediumEditor editor = new MediumEditor();
    editor.setFocusOutlineEnabled(false);
    editor.setJsLoggingEnabled(true);
    // using lorem ipsum to get some text. see demo for dependency
    editor.setContent(Lorem.getHtmlParagraphs(3, 3));
    editor.addBlurListener(value -> {
      preview.setValue(value);
    });
    
The MediumEditor is a vaadin component in its most basic form a `<div>`. So in contrast to the javascript library under the hood, there is no constructor for setting a selector string or a inserting dom elements.

This is a known limitation because you cannot have a single editor configuration for more than one field.
    
    // inherited vaadin component method
    editor.setSizeFull();
    
Other wrapper specific options.
    
* focusOutlineEnabled ... If true the outline / border is shown as soon as the components gets the focus.
* jsLoggingEnabled ... If true logging with `console.log` in the connector script is enabled. Setting this to true, let you review
* content
* addBlurListener ... On every blur event on client side the current content is sent to the server. Register your listener to do sth with it.   

You're able to use the MediumEditor just like that

    protected void init(VaadinRequest request) {
      MediumEditor editor = new MediumEditor();
      setContent(editor);
    }

In this case the default medium-editor options are set. See https://github.com/yabwe/medium-editor#mediumeditor-options for the javascript lib options.

You can overwrite the default options with 

    MediumEditor editor = new MediumEditor();
    editor.configure(
        editor.options()
        // use FontAwesome button labels
        .fontawesomeButtonLabels()
        // start configuring the toolbar
        .toolbar()
          // only this buttons should be included
          .buttons(BuildInButton.BOLD, BuildInButton.ITALIC, BuildInButton.H1, BuildInButton.JUSTIFY_CENTER)
          // the the german translations for the buttons. be aware of the order
          .buttonTranslations("fett", "kursiv", "Ueberschrift1", "zentriert")
          // configuring the button is done, we want to go back and continue configuring other options
          .done()
        // start configuring the placeholder if the component has no content
        .placeholder()
          // the actual text
          .text("Input prompt")
          // configuring the placeholder is done, again we go back and continue configuring other options
          .done()
        // urls added to the content are automatically converted to links 
        .autoLink(true)
        // image dragging
        .imageDragging(false)
        // we're done :)
        .done()
        ); 

The wrappers fluent option api is mostly named after the javascript options documented at https://github.com/yabwe/medium-editor#mediumeditor-options.

I'll probably add more javadoc in the next versions.

### MediumEditorField

The MediumEditorField is great for usage within FieldGroups.

    MediumEditorField editorField = new MediumEditorField(this.i18n.get("messaging.email.content"));
    // get the field's editor for further configuration
    MediumEditor editor = editorField.getEditor();

The `editor` field is then bound to a FieldGroup and might be configured the same way seen above.

## Missing something?

The Vaadin-Medium-Editor is only a wrapper. So if you have any feature requests or found any bugs in the javascript lib please use Medium Editor's issue tracker https://github.com/yabwe/medium-editor/issues 

In all other cases please create a issue at https://github.com/moberwasserlechner/vaadin-medium-editor/issues or contribute to the project yourself. For contribution see the next section.

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-feature-or-bug`)
3. Test your changes to the best of your ability.
5. Commit your changes (`git commit -am 'Describe feature or bug'`)
6. Push to the branch (`git push origin my-feature-or-bug`)
7. Create new Pull Request

### Eclipse

1. Build the Eclipse for the Addon with `./gradlew :addon:cleanEclipse :addon:eclipse`
2. Build the Eclipse for the Addon with `./gradlew :demo:cleanEclipse :demo:eclipse`
3. In Eclipse open File->Import... and choose General->Existing Projects into Workspace for the root folder

### Code Style

Please use the sun coding convention with **2 spaces** instead of tabs for indention. Please do not use tabs at all!

## Demo

### Medium Editor

* http://yabwe.github.io/medium-editor/
* https://yabwe.github.io/medium-editor/demo.html

### Vaadin Medium Editor

1. Clone the repository
2. Run the embedded Tomcat by `./gradlew :demo:run`
3. It starts at `http://localhost:8080`

## License

MIT: https://github.com/moberwasserlechner/vaadin-medium-editor/blob/develop/LICENSE
