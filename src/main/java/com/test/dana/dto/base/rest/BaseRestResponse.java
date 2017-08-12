package com.test.dana.dto.base.rest;

/**
 * Created by mczal on 8/10/17.
 */
public class BaseRestResponse extends BaseRest {

  private String literal;

  private String message;

  private int statusCode;

  public BaseRestResponse(String requestId, HttpCode httpCode) {
    super(requestId);
    this.statusCode = httpCode.code();
    this.literal = httpCode.name();
    this.message = httpCode.message();
  }

  public BaseRestResponse(String requestId, HttpCode httpCode, String message) {
    super(requestId);
    this.statusCode = httpCode.code();
    this.literal = httpCode.name();
    this.message = message;
  }

  public String getLiteral() {
    return literal;
  }

  public void setLiteral(String literal) {
    this.literal = literal;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }
}
