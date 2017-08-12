package com.test.dana.config.model;

import com.test.dana.dto.base.rest.BaseRestResponse;
import com.test.dana.dto.base.rest.HttpCode;

/**
 * Created by mczal on 8/10/17.
 */
public class JwtAuthenticationResponse extends BaseRestResponse {

  private final String token;

  public JwtAuthenticationResponse(String requestId, String token) {
    super(requestId, HttpCode.OK);
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }
}
