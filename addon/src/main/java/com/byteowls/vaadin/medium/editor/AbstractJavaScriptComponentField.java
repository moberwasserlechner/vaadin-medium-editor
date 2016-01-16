package com.byteowls.vaadin.medium.editor;

import java.util.Collection;

import com.vaadin.data.*;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.*;

/**
 * An {@link AbstractJavaScriptComponent} that also implements the {@link Field}
 * interface. This allows for custom JavaScript based fields which support
 * validation and buffered commit. This implementation hasn't been thoroughly
 * tested but it works for most of the basic cases.
 * 
 * @author mpilone
 * @author michael@byteowls.com
 * 
 * @param <T>
 *          the type the field displays
 */
public abstract class AbstractJavaScriptComponentField<T> extends AbstractJavaScriptComponent implements Field<T>, Validatable {

  private static final long serialVersionUID = 3983948480281114159L;
  
  /**
   * The delegate field that is used to implement all the basic field logic.
   * This is required because Java doesn't support multiple inheritance so we
   * use composition with the delegate.
   */
  private AbstractField<T> delegate = new AbstractField<T>() {
    
    private static final long serialVersionUID = -2595281540261583575L;

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.AbstractField#getType()
     */
    @Override
    public Class<? extends T> getType() {
      return AbstractJavaScriptComponentField.this.getType();
    }
  };

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.AbstractComponent#getErrorMessage()
   */
  @Override
  public com.vaadin.server.ErrorMessage getErrorMessage() {
    return delegate.getErrorMessage();
  };

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.AbstractComponent#focus()
   */
  @Override
  public void focus() {
    super.focus();

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.vaadin.data.Property.ValueChangeNotifier#addValueChangeListener(com
   * .vaadin.data.Property.ValueChangeListener)
   */
  @Override
  public void addValueChangeListener(com.vaadin.data.Property.ValueChangeListener listener) {
    delegate.addValueChangeListener(listener);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.vaadin.data.Property.ValueChangeNotifier#removeValueChangeListener(
   * com.vaadin.data.Property.ValueChangeListener)
   */
  @Override
  public void removeValueChangeListener(
      com.vaadin.data.Property.ValueChangeListener listener) {
    delegate.removeValueChangeListener(listener);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.BufferedValidatable#isInvalidCommitted()
   */
  @Override
  public boolean isInvalidCommitted() {
    return delegate.isInvalidCommitted();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.BufferedValidatable#setInvalidCommitted(boolean)
   */
  @Override
  public void setInvalidCommitted(boolean isCommitted) {
    delegate.setInvalidCommitted(isCommitted);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Buffered#commit()
   */
  @Override
  public void commit() throws SourceException, InvalidValueException {
    delegate.commit();

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Buffered#discard()
   */
  @Override
  public void discard() throws SourceException {
    delegate.discard();

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Buffered#setBuffered(boolean)
   */
  @Override
  public void setBuffered(boolean buffered) {
    delegate.setBuffered(buffered);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Buffered#isBuffered()
   */
  @Override
  public boolean isBuffered() {
    return delegate.isBuffered();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Buffered#isModified()
   */
  @Override
  public boolean isModified() {
    return delegate.isModified();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data
   * .Property.ValueChangeEvent)
   */
  @Override
  public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
    delegate.valueChange(event);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.Component.Focusable#getTabIndex()
   */
  @Override
  public int getTabIndex() {
    return delegate.getTabIndex();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.Component.Focusable#setTabIndex(int)
   */
  @Override
  public void setTabIndex(int tabIndex) {
    delegate.setTabIndex(tabIndex);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Property#getValue()
   */
  @Override
  public T getValue() {
    return delegate.getValue();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Property#setValue(java.lang.Object)
   */
  @Override
  public void setValue(T newValue)
      throws com.vaadin.data.Property.ReadOnlyException {
    delegate.setValue(newValue);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.vaadin.data.Property.Viewer#setPropertyDataSource(com.vaadin.data.Property
   * )
   */
  @SuppressWarnings("rawtypes")
  @Override
  public void setPropertyDataSource(Property newDataSource) {
    delegate.setPropertyDataSource(newDataSource);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Property.Viewer#getPropertyDataSource()
   */
  @Override
  public Property<?> getPropertyDataSource() {
    return delegate.getPropertyDataSource();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#addValidator(com.vaadin.data.Validator)
   */
  @Override
  public void addValidator(Validator validator) {
    delegate.addValidator(validator);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#removeValidator(com.vaadin.data.Validator)
   */
  @Override
  public void removeValidator(Validator validator) {
    delegate.removeValidator(validator);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#removeAllValidators()
   */
  @Override
  public void removeAllValidators() {
    delegate.removeAllValidators();

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#getValidators()
   */
  @Override
  public Collection<Validator> getValidators() {
    return delegate.getValidators();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#isValid()
   */
  @Override
  public boolean isValid() {
    return delegate.isValid();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#validate()
   */
  @Override
  public void validate() throws InvalidValueException {
    delegate.validate();

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#isInvalidAllowed()
   */
  @Override
  public boolean isInvalidAllowed() {
    return delegate.isInvalidAllowed();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.data.Validatable#setInvalidAllowed(boolean)
   */
  @Override
  public void setInvalidAllowed(boolean invalidValueAllowed)
      throws UnsupportedOperationException {
    delegate.setInvalidAllowed(invalidValueAllowed);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.Field#isRequired()
   */
  @Override
  public boolean isRequired() {
    return delegate.isRequired();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.Field#setRequired(boolean)
   */
  @Override
  public void setRequired(boolean required) {
    delegate.setRequired(required);

    maybeMarkAsDirty();
  }

  /**
   * Checks if the delegate is dirty and if so, marks this component as dirty as
   * well. This allows the delegate to handle all the logic and optimizations
   * about dirty status while ensuring that the delegate and this component stay
   * in sync. This should normally be called in any mutator.
   */
  protected void maybeMarkAsDirty() {
    markAsDirty();
//    UI ui = getUI();
//    
//    if (ui != null && getUI().getConnectorTracker().isDirty(delegate)) {
//      markAsDirty();
//    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.Field#setRequiredError(java.lang.String)
   */
  @Override
  public void setRequiredError(String requiredMessage) {
    delegate.setRequiredError(requiredMessage);

    maybeMarkAsDirty();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vaadin.ui.Field#getRequiredError()
   */
  @Override
  public String getRequiredError() {
    return delegate.getRequiredError();
  }
  
  @Override
  @Deprecated
  public void addListener(com.vaadin.data.Property.ValueChangeListener listener) {
    addValueChangeListener(listener);
  }

  @Override
  @Deprecated
  public void removeListener(com.vaadin.data.Property.ValueChangeListener listener) {
    removeValueChangeListener(listener);
  }

}
