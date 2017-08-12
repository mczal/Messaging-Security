package com.test.dana.dto.base.rest;

import org.springframework.validation.BindingResult;

/**
 * Created by mczal on 8/10/17.
 */
public class RestException extends RuntimeException {
  private HttpCode httpCode;

  private String requestId;

  public RestException(String requestId, HttpCode httpCode) {
    super(httpCode.name());
    this.httpCode = httpCode;
    this.requestId = requestId;
  }

  public RestException(String requestId, HttpCode httpCode, String message) {
    super(message);
    this.httpCode = httpCode;
    this.requestId = requestId;
  }

  public RestException(String requestId, HttpCode httpCode, BindingResult bindingResult) {
    super(bindingResult.getFieldError().getField() + " [" + bindingResult.getFieldError().
        getDefaultMessage() + "]");
    this.httpCode = httpCode;
    this.requestId = requestId;
  }

  public HttpCode getHttpCode() {
    return httpCode;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}
