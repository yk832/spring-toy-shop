package com.insung.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/view").hasAnyRole("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
//                .successForwardUrl("/t/view")
                .defaultSuccessUrl("/api/test",true)
            .and()
                .csrf().disable();

        return http.build();
    }
}
