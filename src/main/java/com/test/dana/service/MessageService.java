package com.test.dana.service;

import com.test.dana.model.Message;
import com.test.dana.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by mczal on 8/12/17.
 */
public interface MessageService {

  Message findById(String requestId, String id) throws Exception;

  /**
   * For get user inbox
   */
  Page<Message> findByReceiver(User receiver, Pageable pageRequest);

  /**
   * For get user inbox given isRead attribute
   */
  Page<Message> findByReceiverAndIsRead(User receiver, Boolean isRead, Pageable pageRequest);

  /**
   * For get user outbox
   */
  Page<Message> findBySender(User sender, Pageable pageRequest);

  /**
   * For get user outbox sent to given user
   */
  Page<Message> findBySenderAndReceiver(User sender, User receiver, Pageable pageRequest);

  /**
   * For get user inbox received from given user
   */
  Page<Message> findBySenderAndReceiverAndIsBlockedFalse(User sender, User receiver,
      Pageable pageRequest);

  /**
   * For get user outbox sent to given user
   * Given isRead
   */
  Page<Message> findBySenderAndReceiverIsRead(User sender, User receiver, Boolean isRead,
      Pageable pageRequest);

  /**
   * For get user inbox received from given user
   * Given isRead
   */
  Page<Message> findBySenderAndReceiverIsReadAndIsBlockedFalse(User sender, User receiver,
      Boolean isRead, Pageable pageRequest);

  /**
   * Mark isRead to true for all given message id
   */
  void readMessages(String requestId, List<String> ids);

  /**
   * Send new message
   */
  Message save(Message message);

}
