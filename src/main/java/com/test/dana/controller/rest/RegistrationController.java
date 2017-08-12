package com.test.dana.controller.rest;

import com.test.dana.dto.RegisterDtoRequest;
import com.test.dana.dto.base.rest.BaseRestResponse;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import com.test.dana.model.User;
import com.test.dana.service.UserService;
import com.test.dana.utils.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mczal on 8/12/17.
 */
@RestController
@RequestMapping(RegistrationController.ABSOLUTE_PATH)
public class RegistrationController {

  public static final String ABSOLUTE_PATH = "/api/v1";

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private UserService userService;

  @PostMapping(value = "/register",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public BaseRestResponse register(@RequestParam String requestId,
      @Valid @RequestBody RegisterDtoRequest content, BindingResult bindingResult)
      throws Exception {
    if (bindingResult.hasErrors()) {
      throw new RestException(requestId, HttpCode.VALIDATION_CONSTRAINS, bindingResult);
    }

    if (userService.findByUsernameOrEmailCheck(requestId, content.getUsername(), content.getEmail())
        != null)
      throw new RestException(requestId, HttpCode.UNIQUE_CONSTRAINT_VIOLATION,
          "Username or email defined are already used by other user");

    User user = userConverter.convertRegisterDtoRequestToUser(content);
    user.setEnabled(true);
    userService.save(requestId, user);
    return new BaseRestResponse(requestId, HttpCode.OK);
  }

}
