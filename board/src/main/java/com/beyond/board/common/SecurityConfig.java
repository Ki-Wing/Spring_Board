package com.beyond.board.common;

import com.beyond.board.author.service.LoginSucessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // spring securty설정을 customizing하기 위함
@EnableGlobalMethodSecurity(prePostEnabled = true) //pre:사전, post:사후 인증검사
public class SecurityConfig {

    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {
        
        return httpSecurity
                //csrf 공격에 대한 설정은 하지 않겠다.
                .csrf().disable()
                .authorizeRequests()
                    // 인증 제한
                    .antMatchers("/", "/author/create","/author/login-screen")
                .permitAll()
                    // 그 외 요청은 모두 인증 필요
                    .anyRequest().authenticated()
                .and()
//                세션 로그인이 아니라, 토큰 로그인일경우
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .formLogin()
                    .loginPage("/author/login-screen")

//                doLogin메서드는 스프링에서 미리 구현
                    .loginProcessingUrl("/doLogin")
//                다만, doLogin에 넘겨줄 email, password 속성명은 별도 지정
                        .usernameParameter("email")
                        .passwordParameter("password")
                .successHandler(new LoginSucessHandler())
                .and()
//                security에서 구현된 doLogout 기능 그대로 사용
                    .logout().logoutUrl("/doLogout")
                .and()
                .build();
    }
}
