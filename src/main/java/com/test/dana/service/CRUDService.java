package com.test.dana.service;

import com.test.dana.model.BaseEntity;

import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
public interface CRUDService<T extends BaseEntity> {

  void delete(String requestId, String id) throws Exception;

  T findById(String requestId, String id) throws Exception;

  List<T> listAll(String requestId) throws Exception;

  T save(String requestId, T domainObject) throws Exception;

  T update(String requestId, T domainObject) throws Exception;

}
