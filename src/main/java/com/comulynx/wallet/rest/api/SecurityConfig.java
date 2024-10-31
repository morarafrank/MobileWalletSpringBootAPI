package com.comulynx.wallet.rest.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@Order(100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/springboot-rest-api/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().csrf().disable();
        http.headers().frameOptions().disable();
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    // Disable security
//    http.authorizeRequests().anyRequest().permitAll()
//            .and().csrf().disable();
//    }
}

