package com.test.dana.dao;

import com.test.dana.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mczal on 8/10/17.
 */
public interface RoleDAO extends JpaRepository<Role, String> {

  Role findByIdAndMarkForDeleteFalse(String id);

}
