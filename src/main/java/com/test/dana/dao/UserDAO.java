package com.test.dana.dao;

import com.test.dana.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mczal on 8/10/17.
 */
public interface UserDAO extends JpaRepository<User, String> {

  User findByEmailAndMarkForDeleteFalse(String email);

  User findByIdAndMarkForDeleteFalse(String id);

  User findByUsernameAndMarkForDeleteFalse(String username);

  User findByUsernameOrEmail(String username, String email);

  User findByUsernameOrEmailAndMarkForDeleteFalse(String username, String email);

}
