# Introduction

Vaadin 7 wrapper for the medium editor javascript library. https://github.com/yabwe/medium-editor

# Features

* MediumEditor component
* MediumEditorField for simple usage in `com.vaadin.data.fieldgroup.BeanFieldGroup<T>` or `com.vaadin.data.fieldgroup.FieldGroup` 
* Configure the editor's options with a fluent api
* Usage of Vaadin's FontAwesome integration, therefore no addional css file must be included. 
* Localization of build in buttons becomes possible

## MediumEditor

The MediumEditor is already component and therefore has its own `<div>` included. So in contrast to the javascript library under the hood, there is no constructor for setting a selector string or a inserting dom elements.

This is a known limitation because you cannot have a single editor configuration for more than one field. 

# Installation

## Download

[ ![Download](https://api.bintray.com/packages/moberwasserlechner/maven/vaadin-medium-editor/images/download.svg) ](https://bintray.com/moberwasserlechner/maven/vaadin-medium-editor/_latestVersion)

## Maven

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


## Gradle

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

# Example



# Missing something?

The Vaadin-Medium-Editor is only a wrapper. So if you have any feature requests or found any bugs in the javascript lib please use Medium Editor's issue tracker https://github.com/yabwe/medium-editor/issues 

In all other cases please create a issue at https://github.com/byteowls/vaadin-medium-editor/issues or contribute to the project yourself. For contribution see the next section.

# Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Test your changes to the best of your ability.
5. Commit your changes (`git commit -am 'Added some feature'`)
6. Push to the branch (`git push origin my-new-feature`)
7. Create new Pull Request

## Code Style

Please use the sun coding convention with **2 spaces** instead of tabs for indention. Please do not use tabs at all!

# License

MIT: https://github.com/moberwasserlechner/vaadin-medium-editor/blob/master/LICENSE
