package com.test.dana.dto.base.rest;

import com.test.dana.dto.base.BaseResponse;

/**
 * Created by mczal on 8/10/17.
 */
public class RestSingleResponse<T extends BaseResponse> extends BaseRestResponse {

  private T value;

  public RestSingleResponse(String requestId, HttpCode httpCode, String message, T value) {
    super(requestId, httpCode, message);
    this.value = value;
  }

  public RestSingleResponse(String requestId, HttpCode httpCode, T value) {
    super(requestId, httpCode);
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

}
