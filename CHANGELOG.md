# Changelog

### [2.0.0](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.2.2...2.0.0)

* #27 Upgrade to Vaadin 8 and Java 8. For Vaadin 7 use 1.2.2
* #25 Toolbar behind window
* #28 Upgrade to Medium-Editor JS 5.23.0

### [1.2.2](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.2.1...1.2.2)

### [1.2.1](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.2.0...1.2.1)

* Same as 1.2.0 but invalid manifest for vaadin directory.

### [1.2.0](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.1.0...1.2.0)

* #22 Migrate demo application to spring boot, remodel the ui and add more examples
* #23 Server side text change is not assigned to editor if it is in readOnly mode 
* #19 Upgrade to latest medium editor 5.22.0
* #24 Improve contribution section. Instructions for project setup, running demo application locally,... 

### [1.1.0](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.0.2...1.1.0)

* #11 Translations for buildin buttons in German and English. Other languages are very welcome.
* #17 Fontawesome buttons labels are enabled by default
* Added no arg constructor to MediumEditorField
* Rename "BuildinButton" enum to "Buttons" because its shorter and its irrelevant if a button is buildin because supported extensions are added the same way. Breaking change.
* #15 Use medium-editor 5.14.4
* #18 Placeholder does not disappear on serverside content change
* #14 Remove Buttons.IMAGE because imHO its not usable
* Change option state from Bean to JsonValue, because options with multiple datatypes and parameter named like java reserved words (like static) are supported now.
* Add FontSize change button
* #1 Support medium editor themes

### [1.0.2](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.0.1...1.0.2)

* Fix problem in Safari because of user-select=none on Valo Theme v-app style.
* Demo: Name editor and preview panels

### [1.0.1](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.0.0...1.0.1)

* Fix vaadin directory upload restrictions
 * Addon name must not start with "Vaadin"
 * Zip manifest misses 'Vaadin-Package-Version' attribute. see https://github.com/johndevs/gradle-vaadin-plugin/issues/228
* Add Vaadin Directory to installation section in README
* Add Changelog link to README
* Fixing default ANCHOR Button causing an NPE because not implemented
* New allButtons() method for ToolbarBuilder
 
