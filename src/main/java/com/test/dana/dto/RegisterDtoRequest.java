package com.test.dana.dto;

import com.test.dana.dto.base.BaseRequest;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mczal on 8/12/17.
 */
public class RegisterDtoRequest extends BaseRequest {

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  private String password;

  @NotEmpty
  private String username;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
