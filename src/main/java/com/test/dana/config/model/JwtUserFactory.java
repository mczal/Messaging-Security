package com.test.dana.config.model;

import com.test.dana.model.User;
import com.test.dana.model.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mczal on 8/10/17.
 */
public class JwtUserFactory {
  private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<UserRole> userRoles) {
    return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRole().getRole()))
        .collect(Collectors.toList());
  }

  public static JwtUser create(User user) {
    return new JwtUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
        mapToGrantedAuthorities(user.getUserRoles()), user.getEnabled());
  }
}
