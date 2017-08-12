package com.test.dana.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "username")})
public class User extends BaseEntity {

  @OneToMany(fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      mappedBy = "blocker")
  private List<BlockedUser> blockedUsers = new ArrayList();

  @Column(name = "email")
  private String email;

  private Boolean enabled = false;

  @Column(name = "encryptedPassword")
  private String encryptedPassword;

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "receiver")
  private List<Message> messagesIn = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "sender")
  private List<Message> messagesOut = new ArrayList<>();

  @Transient
  private String password;

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "user")
  private List<UserRole> userRoles = new ArrayList<>();

  @Column(name = "username")
  private String username;

  public List<BlockedUser> getBlockedUsers() {
    return blockedUsers;
  }

  public void setBlockedUsers(List<BlockedUser> blockedUsers) {
    this.blockedUsers = blockedUsers;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public List<Message> getMessagesIn() {
    return messagesIn;
  }

  public void setMessagesIn(List<Message> messagesIn) {
    this.messagesIn = messagesIn;
  }

  public List<Message> getMessagesOut() {
    return messagesOut;
  }

  public void setMessagesOut(List<Message> messagesOut) {
    this.messagesOut = messagesOut;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
