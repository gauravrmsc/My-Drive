package com.gauravrmsc.superduperdrive.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  AuthenticationService authenticationService;

  public SecurityConfig(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(this.authenticationService);
  }

  protected void configure(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity.csrf().disable();
//    httpSecurity.cors().disable();
    httpSecurity.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/signup", "/css/**", "/js/**", "/h2/**", "/login", "/result",
            "/error").permitAll().antMatchers(HttpMethod.POST, "/signup", "/h2/**").permitAll()
        .anyRequest().authenticated();/*.and().csrf().disable();*/
    httpSecurity.formLogin().loginPage("/login").permitAll();
    httpSecurity.formLogin().defaultSuccessUrl("/home", true);
    httpSecurity.logout().permitAll();
  }
}
