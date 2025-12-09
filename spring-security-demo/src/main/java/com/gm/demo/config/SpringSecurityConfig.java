package com.gm.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* ② 安全规则：先登录 → 再校验角色 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/test/**").permitAll()
                        .requestMatchers("/user/root").hasRole("root")
                        .requestMatchers("/user/normal").hasRole("normal")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")          // GET 登录页
                        .loginProcessingUrl("/login") // POST 提交
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
