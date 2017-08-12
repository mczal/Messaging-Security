//package com.test.dana.config.security;
//
//import com.test.dana.config.model.JwtAuthenticationEntryPoint;
//import com.test.dana.config.model.JwtAuthenticationTokenFilter;
//import com.test.dana.config.security.service.SpringSecUserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
///**
// * Created by mczal on 8/10/17.
// */
//@Configuration
//@ComponentScan(basePackageClasses = SpringSecUserDetailsServiceImpl.class)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Autowired
//  private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//  @Autowired
//  private UserDetailsService userDetailsService;
//
//  @Bean
//  public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//    return new JwtAuthenticationTokenFilter();
//  }
//
//  @Autowired
//  public void configAuhentication(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }
//
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//
//    http.csrf().disable().antMatcher("/api/**").authorizeRequests().antMatchers("/api/*/auth")
//        .permitAll().antMatchers(HttpMethod.POST, "/api/*/auth/**").authenticated()
//        .antMatchers(HttpMethod.DELETE, "/api/*/auth/**").authenticated()
//        .antMatchers(HttpMethod.PUT, "/api/*/auth/**").authenticated()
//        .antMatchers(HttpMethod.OPTIONS, "/api/*/auth/**").permitAll()
//        .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
//
//        .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//
//        // don't create session
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//
//    // Custom JWT based security filter
//    http.addFilterBefore(authenticationTokenFilterBean(),
//        UsernamePasswordAuthenticationFilter.class);
//
//  }
//
//  @Bean
//  public SessionAuthenticationStrategy getSessionAuthStrategy(SessionRegistry sessionRegistry) {
//    ConcurrentSessionControlAuthenticationStrategy controlAuthenticationStrategy =
//        new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
//
//    return controlAuthenticationStrategy;
//  }
//
//  @Bean
//  public SessionRegistry getSessionRegistry() {
//    return new SessionRegistryImpl();
//  }
//
//  @Bean(name = "passwordEncoder")
//  public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//}
