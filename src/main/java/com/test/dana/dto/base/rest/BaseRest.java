package com.test.dana.dto.base.rest;

import java.io.Serializable;

/**
 * Created by mczal on 8/10/17.
 */
public class BaseRest implements Serializable {

  private String requestId;

  public BaseRest(String requestId) {
    this.requestId = requestId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}
