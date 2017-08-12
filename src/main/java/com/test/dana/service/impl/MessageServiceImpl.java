package com.test.dana.service.impl;

import com.test.dana.dao.MessageDAO;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import com.test.dana.model.Message;
import com.test.dana.model.User;
import com.test.dana.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mczal on 8/12/17.
 */
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageDAO messageDAO;

  @Override
  public Message findById(String requestId, String id) throws Exception {
    Message message = messageDAO.findByIdAndMarkForDeleteFalse(id);
    if (message == null)
      throw new RestException(requestId, HttpCode.NOT_FOUND);
    return message;
  }

  @Override
  public Page<Message> findByReceiver(User receiver, Pageable pageRequest) {
    return messageDAO.findByReceiverAndIsBlockedFalse(receiver, pageRequest);
  }

  @Override
  public Page<Message> findByReceiverAndIsRead(User receiver, Boolean isRead,
      Pageable pageRequest) {
    return messageDAO.findByReceiverAndIsReadAndIsBlockedFalse(receiver, isRead, pageRequest);
  }

  @Override
  public Page<Message> findBySender(User sender, Pageable pageRequest) {
    return messageDAO.findBySender(sender, pageRequest);
  }

  @Override
  public Page<Message> findBySenderAndReceiver(User sender, User receiver, Pageable pageRequest) {
    return messageDAO.findBySenderAndReceiver(sender, receiver, pageRequest);
  }

  @Override
  public Page<Message> findBySenderAndReceiverAndIsBlockedFalse(User sender, User receiver,
      Pageable pageRequest) {
    return messageDAO.findBySenderAndReceiverAndIsBlockedFalse(sender, receiver, pageRequest);
  }

  @Override
  public Page<Message> findBySenderAndReceiverIsRead(User sender, User receiver, Boolean isRead,
      Pageable pageRequest) {
    return messageDAO.findBySenderAndReceiverAndIsRead(sender, receiver, isRead, pageRequest);
  }

  @Override
  public Page<Message> findBySenderAndReceiverIsReadAndIsBlockedFalse(User sender, User receiver,
      Boolean isRead, Pageable pageRequest) {
    return messageDAO
        .findBySenderAndReceiverAndIsReadAndIsBlockedFalse(sender, receiver, isRead, pageRequest);
  }

  @Override
  @Transactional(readOnly = false)
  public void readMessages(String requestId, List<String> ids) {
    ids.forEach(s -> {
      try {
        Message message = findById(requestId, s);
        message.setRead(true);
        save(message);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  @Transactional(readOnly = false)
  public Message save(Message message) {
    return messageDAO.save(message);
  }
}
