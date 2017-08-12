package com.test.dana.service.impl;

import com.test.dana.dao.UserDAO;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import com.test.dana.model.BlockedUser;
import com.test.dana.model.User;
import com.test.dana.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDAO userDAO;

  @Override
  @Transactional(readOnly = false)
  public void blockUser(String requestId, String id, String usernameOrEmail) throws Exception {
    User user = findByIdWithBlockedUsersDetail(requestId, id);
    User blocked = findByUsernameOrEmail(requestId, usernameOrEmail);
    if (user.getId().equals(blocked.getId())) {
      throw new RestException(requestId, HttpCode.BAD_REQUEST,
          "Blocker and blocked user has exact same id");
    }

    if (user.getBlockedUsers().stream()
        .noneMatch(blockedUser -> blockedUser.getBlocked().getId().equals(blocked.getId()))) {
      BlockedUser blockedUser = new BlockedUser();
      blockedUser.setBlocker(user);
      blockedUser.setBlocked(blocked);

      user.getBlockedUsers().add(blockedUser);
      userDAO.save(user);
    }
  }

  @Override
  @Transactional(readOnly = false)
  public void delete(String requestId, String id) throws Exception {
    userDAO.delete(id);
  }

  @Override
  public User findById(String requestId, String id) throws Exception {
    User user = userDAO.findByIdAndMarkForDeleteFalse(id);
    if (user == null) {
      throw new RestException(requestId, HttpCode.NOT_FOUND);
    }
    return user;
  }

  //  @Override
  //  public User findByIdWithMessagesInDetail(String requestId, String id) throws Exception {
  //    User user = findById(requestId, id);
  //    Hibernate.initialize(user.getMessagesIn());
  //    return user;
  //  }
  //
  //  @Override
  //  public User findByIdWithMessagesOutDetail(String requestId, String id) throws Exception {
  //    User user = findById(requestId, id);
  //    Hibernate.initialize(user.getMessagesOut());
  //    return user;
  //  }
  //
  //  @Override
  //  public User findByIdWithRolesDetail(String requestId, String id) throws Exception {
  //    User user = findById(requestId, id);
  //    Hibernate.initialize(user.getUserRoles());
  //    return user;
  //  }

  @Override
  public User findByIdWithBlockedUsersDetail(String requestid, String id) throws Exception {
    User user = findById(requestid, id);
    Hibernate.initialize(user.getBlockedUsers());
    return user;
  }

  @Override
  public User findByUsernameOrEmail(String requestId, String usernameOrEmail) throws Exception {
    User user =
        userDAO.findByUsernameOrEmailAndMarkForDeleteFalse(usernameOrEmail, usernameOrEmail);
    if (user == null) {
      throw new RestException(requestId, HttpCode.NOT_FOUND);
    }
    return user;
  }

  @Override
  public User findByUsernameOrEmailCheck(String requestId, String username, String email) {
    return userDAO.findByUsernameOrEmailAndMarkForDeleteFalse(username, email);
  }

  @Override
  public User findByUsernameOrEmailWithBlockedUsersDetail(String requestId, String usernameOrEmail)
      throws Exception {
    User user = findByUsernameOrEmail(requestId, usernameOrEmail);
    Hibernate.initialize(user.getBlockedUsers());
    return user;
  }

  @Override
  public User findByUsernameOrEmailWithRolesDetail(String requestId, String usernameOrEmail)
      throws Exception {
    User user = findByUsernameOrEmail(requestId, usernameOrEmail);
    Hibernate.initialize(user.getUserRoles());
    return user;
  }

  //  public List<Message> getUserInMessages(String requestId, String id, MessageType messageType)
  //      throws Exception {
  //
  //  }

  @Override
  public List<User> listAll(String requestId) throws Exception {
    return userDAO.findAll();
  }

  @Override
  @Transactional(readOnly = false)
  public User save(String requestId, User domainObject) throws Exception {
    domainObject.setEncryptedPassword(passwordEncoder.encode(domainObject.getPassword()));
    return userDAO.save(domainObject);
  }

  @Override
  @Transactional(readOnly = false)
  public User update(String requestId, User domainObject) throws Exception {
    return userDAO.save(domainObject);
  }
}
