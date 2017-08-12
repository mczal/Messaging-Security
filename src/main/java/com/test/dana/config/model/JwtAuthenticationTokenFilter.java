package com.test.dana.config.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mczal on 8/10/17.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
  private final Log logger = LogFactory.getLog(this.getClass());

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService jwtUserDetailsService;

  @Value("${jwt.header}")
  private String tokenHeader;

  public JwtAuthenticationTokenFilter() {
    /**
     * nothing todo here
     * */
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    String header = request.getHeader(this.tokenHeader);

    logger.debug("running doFilterInternal for header -> " + header + " in " + this.getClass());

    if (header != null && header.startsWith("Bearer ")) {
      String authToken = header.substring(7);
      String username = jwtTokenUtil.getUsernameFromToken(authToken);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        // It is not compelling necessary to load the user details from the database. You could also store the information
        // in the token and read it from it. It's up to you ;)
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

        // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
        // the database compellingly. Again it's up to you ;)
        if (jwtTokenUtil.validateToken(authToken, userDetails)) {
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(userDetails, null,
                  userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    }

    chain.doFilter(request, response);
  }
}
