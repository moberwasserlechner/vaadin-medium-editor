# Changelog

### [1.1.0](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.0.2...1.1.0)

* #11 Translations for buildin buttons in German and English. Other languages are very welcome.by
* #17 Fontawesome buttons labels are enabled by default
* Added no arg constructor to MediumEditorField
* Rename "BuildinButton" enum to "Buttons" because its shorter and its irrelevant if a button is buildin because supported extensions are added the same way. Breaking change.

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
 
