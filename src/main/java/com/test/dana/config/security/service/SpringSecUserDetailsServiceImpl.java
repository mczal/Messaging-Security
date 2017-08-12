//package com.test.dana.config.security.service;
//
//import com.test.dana.model.User;
//import com.test.dana.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by mczal on 8/10/17.
// */
//@Service("userDetailsService")
//@Transactional(readOnly = true)
//public class SpringSecUserDetailsServiceImpl implements UserDetailsService {
//
//  private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//  @Autowired
//  private UserService userService;
//
//  @Autowired
//  private Converter<User, UserDetails> userToUserDetails;
//
//  @Override
//  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//    logger.debug("loadUserByUsername '" + s + "' in userDetailsService");
//    try {
//      UserDetailsImpl userDetails = (UserDetailsImpl) userToUserDetails
//          .convert(userService.findByUsernameOrEmail("admin", s));
//      return userDetails;
//    } catch (Exception e) {
//      throw new UsernameNotFoundException("Username or email not found !");
//    }
//  }
//}
