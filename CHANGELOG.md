# Changelog

### [1.0.2](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.0.1...1.0.2)

* Fix problem in Safari because of user-select=none on Valo Theme v-app style.

### [1.0.1](https://github.com/moberwasserlechner/vaadin-medium-editor/compare/1.0.0...1.0.1)

* Fix vaadin directory upload restrictions
 * Addon name must not start with "Vaadin"
 * Zip manifest misses 'Vaadin-Package-Version' attribute. see https://github.com/johndevs/gradle-vaadin-plugin/issues/228
* Add Vaadin Directory to installation section in README
* Add Changelog link to README
* Fixing default ANCHOR Button causing an NPE because not implemented
* New allButtons() method for ToolbarBuilder
 
