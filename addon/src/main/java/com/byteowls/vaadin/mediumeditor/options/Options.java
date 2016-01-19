package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

public class Options implements Serializable {

  private static final long serialVersionUID = 3972297834616344210L;

  private int delay = 0;
  private boolean disableReturn = false;
  private boolean disableDoubleReturn = false;
  private boolean disableExtraSpaces = false;
  private boolean disableEditing = false;

  public int getDelay() {
    return delay;
  }
  public void setDelay(int delay) {
    this.delay = delay;
  }
  public boolean isDisableReturn() {
    return disableReturn;
  }
  public void setDisableReturn(boolean disableReturn) {
    this.disableReturn = disableReturn;
  }
  public boolean isDisableDoubleReturn() {
    return disableDoubleReturn;
  }
  public void setDisableDoubleReturn(boolean disableDoubleReturn) {
    this.disableDoubleReturn = disableDoubleReturn;
  }
  public boolean isDisableExtraSpaces() {
    return disableExtraSpaces;
  }
  public void setDisableExtraSpaces(boolean disableExtraSpaces) {
    this.disableExtraSpaces = disableExtraSpaces;
  }
  public boolean isDisableEditing() {
    return disableEditing;
  }
  public void setDisableEditing(boolean disableEditing) {
    this.disableEditing = disableEditing;
  }

}
