package com.test.dana.config.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by mczal on 8/10/17.
 */
@Component
public class JwtUser implements UserDetails {
  private static final long serialVersionUID = -2651650362118490283L;

  private Collection<SimpleGrantedAuthority> authorities;

  private String email;

  private boolean enabled;

  private String id;

  private String password;

  private String username;

  public JwtUser() {
  }

  public JwtUser(String id, String username, String email, String password,
      Collection<SimpleGrantedAuthority> authorities, boolean enabled) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.enabled = enabled;
  }

  @Override
  public Collection<SimpleGrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @JsonIgnore
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return "JwtUser{ email='" + email + '\'' + ", enabled=" + enabled + ", id='" + id + '\''
        + ", password='" + password + '\'' + ", username='" + username + '\'' + '}';
  }
}
