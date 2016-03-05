# Changelog

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
 
