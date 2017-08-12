package com.test.dana.config.security.service;

import com.test.dana.config.model.JwtUser;
import com.test.dana.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mczal on 8/10/17.
 */
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

  @Override
  public UserDetails convert(User user) {
    JwtUser userDetails = new JwtUser();
    userDetails.setId(user.getId());
    userDetails.setUsername(user.getUsername());
    userDetails.setPassword(user.getEncryptedPassword());
    userDetails.setEnabled(user.getEnabled());
    userDetails.setEmail(user.getEmail());

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    user.getUserRoles().forEach(userRole -> {
      authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRole()));
    });

    userDetails.setAuthorities(authorities);

    return userDetails;
  }
}
