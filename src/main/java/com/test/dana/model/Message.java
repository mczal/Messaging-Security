package com.test.dana.model;

import javax.persistence.*;

/**
 * Created by mczal on 8/10/17.
 */
@Entity
public class Message extends BaseEntity {

  @Column(name = "isBlocked")
  private Boolean isBlocked = false;

  @Column(name = "isRead")
  private Boolean isRead = false;

  @Lob
  @Column(name = "message",
      length = 65535)
  private String message;

  @ManyToOne
  @JoinColumn(name = "receiverId")
  private User receiver;

  @ManyToOne
  @JoinColumn(name = "senderId")
  private User sender;

  public Boolean getBlocked() {
    return isBlocked;
  }

  public void setBlocked(Boolean blocked) {
    isBlocked = blocked;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getRead() {
    return isRead;
  }

  public void setRead(Boolean read) {
    isRead = read;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  @Override
  public String toString() {
    return "Message{" + "isRead=" + isRead + ", message='" + message + '\'' + ", receiver="
        + receiver + ", sender=" + sender + '}';
  }
}
