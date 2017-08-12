package com.test.dana.dto.user.response;

import com.test.dana.dto.base.BaseResponse;

/**
 * Created by mczal on 8/12/17.
 */
public class UserDtoResponse extends BaseResponse {

  private String email;

  private String username;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
