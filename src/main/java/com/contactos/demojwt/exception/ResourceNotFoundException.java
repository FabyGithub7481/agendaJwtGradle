package com.contactos.demojwt.exception;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException() {

  }

  public static <T> List<T> requireNotEmpty(List<T> items) throws ResourceNotFoundException {
    if (items.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return items;
  }
}
