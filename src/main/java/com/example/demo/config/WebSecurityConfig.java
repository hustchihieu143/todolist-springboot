package com.example.demo.config;

import lombok.AllArgsConstructor;

import com.example.demo.security.jwt.AuthEntryPointJwt;
import com.example.demo.security.jwt.JwtTokenFilter;
import com.example.demo.security.oauth2.CustomOAuth2User;
import com.example.demo.security.services.UserDetailsServiceImpl;
import com.example.demo.service.CustomOAuth2UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public JwtTokenFilter authenticationJwtTokenFilter() {
    return new JwtTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private CustomOAuth2UserService oauthUserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers("/auth/**", "/login", "/oauth/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .oauth2Login()
        .loginPage("/login")
        .userInfoEndpoint()
        .userService(oauthUserService)
        .and()
        .successHandler((AuthenticationSuccessHandler) new AuthenticationSuccessHandler() {

          @Override
          public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
              Authentication authentication) throws IOException, ServletException {

            CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

            System.out.println("okokoko");
          }
        });
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
