 package com.kyron.rmi;
public class RecordNotFoundException extends Exception
{
  public RecordNotFoundException() {
  }
  public RecordNotFoundException(String msg)
  {
    super(msg);
  }
  public RecordNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public RecordNotFoundException(Throwable cause) {
    super(cause);
  }

}