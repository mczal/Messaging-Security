package com.test.dana.dto.message.response;

import com.test.dana.dto.base.BaseResponse;
import com.test.dana.dto.user.response.UserDtoResponse;

/**
 * Created by mczal on 8/12/17.
 */
public class MessageInDtoResponse extends BaseResponse {

  private Boolean isRead;

  private String message;

  private UserDtoResponse sender;

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

  public UserDtoResponse getSender() {
    return sender;
  }

  public void setSender(UserDtoResponse sender) {
    this.sender = sender;
  }

}
