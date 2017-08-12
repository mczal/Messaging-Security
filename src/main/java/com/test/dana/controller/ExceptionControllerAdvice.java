package com.test.dana.controller;

import com.test.dana.dto.base.rest.BaseRestResponse;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mczal on 8/10/17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(RestException.class)
  @ResponseBody
  public BaseRestResponse handleRestException(HttpServletResponse res, HttpServletRequest req,
      RestException restException) {
    res.setStatus(restException.getHttpCode().code());
    if (restException.getRequestId().equals("null")) {
      restException.setRequestId(req.getParameter("requestId"));
    }
    return new BaseRestResponse(restException.getRequestId(), restException.getHttpCode(),
        restException.getMessage());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseBody
  public BaseRestResponse handleRestException(HttpServletResponse res, HttpServletRequest req,
      UsernameNotFoundException usernameNotFoundException) {
    res.setStatus(HttpCode.NOT_FOUND.code());
    return new BaseRestResponse(req.getParameter("requestId"), HttpCode.NOT_FOUND,
        usernameNotFoundException.getMessage());
  }

}
