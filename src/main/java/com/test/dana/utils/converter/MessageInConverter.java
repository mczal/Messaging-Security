package com.test.dana.utils.converter;

import com.test.dana.dto.message.response.MessageInDtoResponse;
import com.test.dana.dto.user.response.UserDtoResponse;
import com.test.dana.model.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by mczal on 8/12/17.
 */
@Component
public class MessageInConverter implements Converter<Message, MessageInDtoResponse> {

  @Override
  public MessageInDtoResponse convert(Message message) {
    MessageInDtoResponse dto = new MessageInDtoResponse();
    BeanUtils.copyProperties(message, dto, "receiver", "sender");
    UserDtoResponse dtoSender = new UserDtoResponse();
    BeanUtils.copyProperties(message.getSender(), dtoSender);
    dto.setSender(dtoSender);
    return dto;
  }
}
