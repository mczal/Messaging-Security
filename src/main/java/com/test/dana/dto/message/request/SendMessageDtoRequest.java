package com.test.dana.dto.message.request;

import com.test.dana.dto.base.BaseRequest;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mczal on 8/12/17.
 */
public class SendMessageDtoRequest extends BaseRequest {

  @NotEmpty
  private String message;

  /**
   * Username or email
   */
  @NotEmpty
  private String recipientEmailOrUsername;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getRecipientEmailOrUsername() {
    return recipientEmailOrUsername;
  }

  public void setRecipientEmailOrUsername(String recipientEmailOrUsername) {
    this.recipientEmailOrUsername = recipientEmailOrUsername;
  }
}
