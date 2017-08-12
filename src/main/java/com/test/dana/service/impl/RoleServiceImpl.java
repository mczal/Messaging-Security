package com.test.dana.service.impl;

import com.test.dana.dao.RoleDAO;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import com.test.dana.model.Role;
import com.test.dana.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDAO roleDAO;

  @Override
  @Transactional(readOnly = false)
  public void delete(String requestId, String id) throws Exception {
    roleDAO.delete(id);
  }

  @Override
  public Role findById(String requestId, String id) throws Exception {
    Role role = roleDAO.findByIdAndMarkForDeleteFalse(id);
    if (role == null) {
      throw new RestException(requestId, HttpCode.NOT_FOUND);
    }
    return role;
  }

  @Override
  public List<Role> listAll(String requestId) throws Exception {
    return roleDAO.findAll();
  }

  @Override
  @Transactional(readOnly = false)
  public Role save(String requestId, Role domainObject) throws Exception {
    return roleDAO.save(domainObject);
  }

  @Override
  @Transactional(readOnly = false)
  public Role update(String requestId, Role domainObject) throws Exception {
    /**
     * Expecting to throw exception when not found
     * */
    findById(requestId, domainObject.getId());
    return roleDAO.save(domainObject);
  }
}