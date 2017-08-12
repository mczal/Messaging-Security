package com.test.dana.dao;

import com.test.dana.model.Message;
import com.test.dana.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mczal on 8/12/17.
 */
public interface MessageDAO extends JpaRepository<Message, String> {

  Message findByIdAndMarkForDeleteFalse(String id);

  /**
   * For get user inbox
   */
  Page<Message> findByReceiverAndIsBlockedFalse(User receiver, Pageable pageRequest);

  /**
   * For get user inbox given isRead attribute
   */
  Page<Message> findByReceiverAndIsReadAndIsBlockedFalse(User receiver, Boolean isRead,
      Pageable pageRequest);

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
   * And
   * Given isRead
   */
  Page<Message> findBySenderAndReceiverAndIsRead(User sender, User receiver, Boolean isRead,
      Pageable pageRequest);

  /**
   * For get user inbox received from given user //notnecc
   * Given isRead
   */
  Page<Message> findBySenderAndReceiverAndIsReadAndIsBlockedFalse(User sender, User receiver,
      Boolean isRead, Pageable pageRequest);

}
