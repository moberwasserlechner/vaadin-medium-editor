package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.mediumeditor.options.Options.OptionsBuilder;

public class KeyboardCommands implements Serializable {
  
  private static final long serialVersionUID = 8485268339849110774L;

  public List<KeyboardCommand> commands;
  
  private KeyboardCommands(KeyboardCommandsBuilder builder) {
    commands = builder.commands;
  }
  
  public static KeyboardCommandsBuilder builder(OptionsBuilder optionsBuilder) {
    return new KeyboardCommandsBuilder(optionsBuilder);
  }
  
  public static class KeyboardCommandsBuilder {
    // reference to parent
    private OptionsBuilder optionsBuilder;

    private List<KeyboardCommand> commands;
    
    public KeyboardCommandsBuilder(OptionsBuilder optionsBuilder) {
      this.optionsBuilder = optionsBuilder;
    }
    
    public KeyboardCommandsBuilder command(String command, String key, boolean meta, boolean shift, boolean alt) {
      if (command != null && key != null) {
        if (commands == null) {
          commands = new ArrayList<KeyboardCommand>();
        }
        KeyboardCommand c = new KeyboardCommand();
        c.command = command;
        c.key = key;
        c.meta = meta;
        c.shift = shift;
        c.alt = alt;
        commands.add(c);
      }
      return this;
    }
    
    public OptionsBuilder done() {
      return optionsBuilder;
    }

    KeyboardCommands build() {
      return new KeyboardCommands(this);
    }
    
  }

}
