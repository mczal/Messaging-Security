package com.test.dana.config.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by mczal on 8/10/17.
 */
public class JwtAuthenticationRequest implements Serializable {

  @NotEmpty
  private String password;

  @NotEmpty
  private String username;

  public JwtAuthenticationRequest() {
  }

  public JwtAuthenticationRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
