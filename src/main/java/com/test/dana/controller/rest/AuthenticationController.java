package com.test.dana.controller.rest;

import com.test.dana.config.model.*;
import com.test.dana.dto.base.rest.BaseRestResponse;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import com.test.dana.model.User;
import com.test.dana.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mczal on 8/10/17.
 */
@RestController
@RequestMapping(AuthenticationController.ABSOLUTE_PATH)
public class AuthenticationController {

  public static final String ABSOLUTE_PATH = "/api/v1";

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login",
      method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestParam String requestId,
      @RequestBody @Valid JwtAuthenticationRequest authenticationRequest,
      BindingResult bindingResult, Device device) throws Exception {

    if (bindingResult.hasErrors()) {
      throw new RestException(requestId, HttpCode.VALIDATION_CONSTRAINS,
          bindingResult.getFieldErrors().toString());
    }

    // Perform the security
    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
            authenticationRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-security so we can generate token
    //    final UserDetails userDetails =
    //            jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    User user = userService.findByUsernameOrEmail(requestId, authenticationRequest.getUsername());
    JwtUser jwtUser = JwtUserFactory.create(user);
    final String token = jwtTokenUtil.generateToken(jwtUser, device);

    // Return the token
    return ResponseEntity.ok(new JwtAuthenticationResponse(requestId, token));
  }

  @GetMapping(value = "/auth/test-authenticated-entry",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseRestResponse test(@RequestParam String requestId) {
    return new BaseRestResponse(requestId, HttpCode.OK);
  }


}
