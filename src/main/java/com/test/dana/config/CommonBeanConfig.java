package com.test.dana.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mczal on 8/10/17.
 */
@Configuration
@EnableJpaRepositories("com.test.dana.dao")
@EnableJpaAuditing
public class CommonBeanConfig extends WebMvcConfigurerAdapter {

  public static class SecurityAuditor implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null) {
        return auth.getName();
      } else {
        return "JPABootstrap.class";
      }
    }
  }

  @Bean
  public AuditingEntityListener createAuditingListener() {
    return new AuditingEntityListener();
  }

  @Bean
  public AuditorAware<String> createAuditorProvider() {
    return new SecurityAuditor();
  }

  @Bean
  public RestTemplate createRestTemplateFromRestTemplateBuilder(
      RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

}
