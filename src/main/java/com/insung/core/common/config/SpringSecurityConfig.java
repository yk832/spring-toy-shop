package com.insung.core.common.config;

import com.insung.core.common.security.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/acs/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/**").permitAll()
//                .antMatchers("/common/**","/user/**","/api/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .successHandler(new LoginSuccessHandler())
            .and()
                .csrf().disable();

        return http.build();
    }
}
