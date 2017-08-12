package com.test.dana.utils.converter;

import com.test.dana.dto.message.response.MessageOutDtoResponse;
import com.test.dana.model.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by mczal on 8/12/17.
 */
@Component
public class MessageOutConverter implements Converter<Message, MessageOutDtoResponse> {

  @Override
  public MessageOutDtoResponse convert(Message message) {
    MessageOutDtoResponse dto = new MessageOutDtoResponse();
    BeanUtils.copyProperties(message, dto, "sender");
    return dto;
  }

}
