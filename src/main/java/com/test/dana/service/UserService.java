package com.test.dana.service;

import com.test.dana.model.User;

/**
 * Created by mczal on 8/10/17.
 */
public interface UserService extends CRUDService<User> {

  void blockUser(String requestId, String id, String usernameOrEmail) throws Exception;

  //  User findByIdWithMessagesInDetail(String requestId, String id) throws Exception;
  //
  //  User findByIdWithMessagesOutDetail(String requestId, String id) throws Exception;

  //  User findByIdWithRolesDetail(String requestId, String id) throws Exception;

  User findByIdWithBlockedUsersDetail(String requestid, String id) throws Exception;

  User findByUsernameOrEmail(String requestId, String usernameOrEmail) throws Exception;

  User findByUsernameOrEmailCheck(String requestId, String username, String email);

    User findByUsernameOrEmailWithBlockedUsersDetail(String requestId, String usernameOrEmail)
        throws Exception;

  User findByUsernameOrEmailWithRolesDetail(String requestId, String usernameOrEmail)
      throws Exception;

}
