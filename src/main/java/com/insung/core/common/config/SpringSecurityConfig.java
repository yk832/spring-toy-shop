package com.insung.core.common.config;

import com.insung.core.common.security.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler customFailureHandler;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/acs/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_processing")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(customFailureHandler); // 로그인 실패 핸들러
    }

    // 기존에 빈으로 등록하지 않고 사용할 당시 암호화된 비밀번호 앞에 {bcrypt} 을 문자열로 추가해줬다.
    // 빈으로 등록하고 기존에 있던 아이디로 로그인 시도하니까 실패하는 현상이 발생..
    // 앞에 bcrypt 를 추가해주던 로직을 삭제 후 신규 아이디 등록 > 로그인 하니까 성공
    // 결론 : 앞에 bcrypt 문자열을 추가해주지 않아도 된다.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
