package com.matheus_moraes.transactions_api.exception;

public class UnprocessableEntityException extends RuntimeException {
  public UnprocessableEntityException(String message) {
    super(message);
  }
}
