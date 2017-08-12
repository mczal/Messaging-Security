package com.test.dana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mczal on 8/10/17.
 */
@Controller
public class IndexController {
  @RequestMapping({"/", ""})
  public String index(Model model) {
    return "redirect:/swagger-ui.html";
  }

  @RequestMapping(method = RequestMethod.GET,
      value = "/ws-messaging")
  public String wsMessaging() {
    return "websocket";
  }

}
