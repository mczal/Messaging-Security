package com.test.dana.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by mczal on 8/12/17.
 */
@Entity
public class BlockedUser extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "blockedId")
  private User blocked;

  @ManyToOne
  @JoinColumn(name = "blockerId")
  private User blocker;

  public User getBlocked() {
    return blocked;
  }

  public void setBlocked(User blocked) {
    this.blocked = blocked;
  }

  public User getBlocker() {
    return blocker;
  }

  public void setBlocker(User blocker) {
    this.blocker = blocker;
  }
}
