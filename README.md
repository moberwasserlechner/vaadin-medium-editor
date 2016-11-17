# Vaadin Medium Editor [![Bintray](https://img.shields.io/bintray/v/moberwasserlechner/maven/vaadin-medium-editor.svg)](https://bintray.com/moberwasserlechner/maven/vaadin-medium-editor/_latestVersion) [![PayPal](https://img.shields.io/badge/%24-donate-0CB3EB.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=N8VS2P9233NJQ) [![License](https://img.shields.io/badge/license-MIT-B34ED4.svg)](https://github.com/moberwasserlechner/vaadin-medium-editor/blob/master/LICENSE)

Vaadin 7 wrapper for the medium editor javascript library. https://github.com/yabwe/medium-editor

## Features

* MediumEditor component
* MediumEditorField for simple usage in `com.vaadin.data.fieldgroup.BeanFieldGroup<T>` or `com.vaadin.data.fieldgroup.FieldGroup` 
* Configure the editor's options with a fluent api
* Usage of Vaadin's FontAwesome integration, therefore no addional css file must be included.
* Localization for English and German 
* Custom localization by configuration 

## Demo

### Vaadin Addon

* http://vaadin-demos.qqjtxeeuih.eu-central-1.elasticbeanstalk.com:5700

If you want to run the demo application locally, see the [Contribution Section](#run-the-demo-local)

### Medium Editor

* http://yabwe.github.io/medium-editor/
* https://yabwe.github.io/medium-editor/demo.html

## Installation

### Download

[![Bintray](https://img.shields.io/bintray/v/moberwasserlechner/maven/vaadin-medium-editor.svg)](https://bintray.com/moberwasserlechner/maven/vaadin-medium-editor/_latestVersion)

### Vaadin Directory

Get the addon from 
https://vaadin.com/directory#!addon/medium-editor-wrapper. 

Vaadin runs its own Maven repository and you can download the addon there as well, but you will need to create a free vaadin account first.

### Maven

Repository

    <repositories>
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
      <dependency>
        <groupId>com.byteowls</groupId>
        <artifactId>vaadin-medium-editor</artifactId>
        <version>1.2.1</version>
      </dependency>
    </dependencies>


### Gradle

Repository

    repositories {
      jcenter()
    }
     
Dependency

    dependencies {
      compile ("com.byteowls:vaadin-medium-editor:1.2.1")
    }
## Usage

For more examples please see the [demo app](#vaadin-addon)

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
          .buttons(Buttons.BOLD, Buttons.ITALIC, Buttons.H1, Buttons.JUSTIFY_CENTER)
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


## Prerequisite

### Addon
* JDK 7 or higher
* Vaadin 7.4 or higher

### Demo
* JDK 8 (because of Lambdas)
* Vaadin 7.7.0+


## Missing something?

The Vaadin-Medium-Editor is only a wrapper. So if you have any feature requests or found any bugs in the javascript lib please use Medium Editor's issue tracker https://github.com/yabwe/medium-editor/issues 

In all other cases please create a issue at https://github.com/moberwasserlechner/vaadin-medium-editor/issues or contribute to the project yourself. For contribution see the next section.

## Contribute

### Setup Eclipse

1. Fork repo
2. Open command line
3. Clone your fork `git@github.com:USERNAME/vaadin-medium-editor.git`
4. `cd vaadin-medium-editor`
5. Build eclipse meta data `./gradlew cleanEclipse eclipse`
6. Open Eclipse
7. File -> Import... -> General -> Existing Projects into Workspace
8. Browse to your git repository
9. Check the option "Search for nested projects"
10. Check all 3 projects
11. Press finish

This should take not more than 1-2 minutes. You does not need to use any gradle eclipse plugins. 

### Fix a bug or create a new feature

Please do not mix more than one issue in a feature branch. Each feature/bugfix should have its own branch and its own Pull Request (PR).

1. Create a issue and describe what you want to do at [Issue Tracker](https://github.com/moberwasserlechner/vaadin-medium-editor/issues)
2. Create your feature branch (`git checkout -b feature/my-feature` or `git checkout -b bugfix/my-bugfix`)
3. Test your changes to the best of your ability.
4. Add a demo view to the demo application 
5. Commit your changes (`git commit -m 'Describe feature or bug'`)
6. Push to the branch (`git push origin feature/my-feature`)
7. Create a Github Pull Request

### Run the demo local

The demo application is based on Spring Boot. So its possible to run the Demo as Java Application right out of Eclipse, there is not servlet container needed as Spring Boot has a embedded Tomcat 8 included.

1. Open "Debug Configurations..." dialog
2. Create a new "Java Application"
3. Choose the "vaadin-medium-editor-demo" project
4. Use "com.byteowls.vaadin.mediumeditor.demo.AddonDemoApplication" as Main class
5. Set `-Dprofile=dev` as VM argument. This ensures that source code panel in the demo is correctly filled while developing.
6. Browse to `http://localhost:8080/`

### Translations

Additional languages are very welcome.

ResourceBundles with properties files for the actual translations are used. They are located at

    https://github.com/moberwasserlechner/vaadin-medium-editor/tree/master/addon/src/main/resources/com/byteowls/vaadin/mediumeditor/options
 
Supported languages are:

* English (fallback)
* German
 
### Code Style

Please use the sun coding convention. Please do not use tabs at all!

## License

MIT. Please see [LICENSE](https://github.com/moberwasserlechner/vaadin-medium-editor/blob/master/LICENSE).

## Change Log

Please see [CHANGELOG](https://github.com/moberwasserlechner/vaadin-medium-editor/blob/master/CHANGELOG.md).