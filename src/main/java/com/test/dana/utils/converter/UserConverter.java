package com.test.dana.utils.converter;

import com.test.dana.dto.RegisterDtoRequest;
import com.test.dana.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by mczal on 8/12/17.
 */
@Component
public class UserConverter {

  public User convertRegisterDtoRequestToUser(RegisterDtoRequest registerDtoRequest) {
    User user = new User();
    BeanUtils.copyProperties(registerDtoRequest, user);
    return user;
  }

}
