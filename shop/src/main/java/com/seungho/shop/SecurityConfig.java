package com.seungho.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;

// 아래 어노테이션 두개는 Spring Security 설정 가능
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    // 어떤 페이지를 로그인검사할지 설정가능
    // FilterChain => 모든 유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드 담는 곳
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 잠시 끄자
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                // permitAll() => 아무나 접속 허용
                authorize.requestMatchers("/**").permitAll()
        );
        return http.build();
    }

}
