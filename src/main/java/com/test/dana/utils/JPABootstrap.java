package com.test.dana.utils;

import com.test.dana.model.User;
import com.test.dana.service.RoleService;
import com.test.dana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by mczal on 8/10/17.
 */
@Component
public class JPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private static final String MOCK_REQUESTID = "JPABootstrap.class";

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  private void loadUser() throws Exception {
    User user = new User();
    user.setEmail("mczal@dana.id");
    user.setUsername("mczal");
    user.setPassword("123");
    user.setEnabled(true);
    userService.save(MOCK_REQUESTID, user);
  }

  private void loadUserRole() {

  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      loadUser();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
