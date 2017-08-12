//package com.test.dana.config.security.service;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
///**
// * Created by mczal on 8/10/17.
// */
//@Component
//public class UserDetailsImpl implements UserDetails {
//  private static final long serialVersionUID = 812786791414125834L;
//
//  private Collection<SimpleGrantedAuthority> authorities;
//
//  private Boolean enabled;
//
//  private String password;
//
//  private String username;
//
//  public UserDetailsImpl() {
//  }
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return authorities;
//  }
//
//  public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
//    this.authorities = authorities;
//  }
//
//  public Boolean getEnabled() {
//    return enabled;
//  }
//
//  @Override
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  @Override
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return enabled;
//  }
//
//  public void setEnabled(Boolean enabled) {
//    this.enabled = enabled;
//  }
//
//  @Override
//  public String toString() {
//    return "UserDetailsImpl{" + "enabled=" + enabled + ", password='" + password + '\''
//        + ", username='" + username + '\'' + '}';
//  }
//}
