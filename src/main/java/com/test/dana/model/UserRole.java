package com.test.dana.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by mczal on 8/10/17.
 */
@Entity
public class UserRole extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "roleId")
  private Role role;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
