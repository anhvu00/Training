 package com.kyron.rmi;
/**
 *A generic checked Exception used by this application for reporting any business logic exception.
 *Instead of creating multiple Exception subclasses only one subclass is used for simplicity.
 */
public class CheckedException extends Exception {

  public CheckedException() {
  }

  public CheckedException(String message) {
    super(message);
  }

  public CheckedException(String message, Throwable cause) {
    super(message, cause);
  }

  public CheckedException(Throwable cause) {
    super(cause);
  }
}