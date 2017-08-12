package com.test.dana.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
@Entity
public class Role extends BaseEntity {

  @Column(name = "role")
  private String role;

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "role")
  private List<UserRole> userRoles = new ArrayList<>();

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public List<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

}
