package com.gm.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * spring-security UserDetailsService接口实现类
 *
 * @author gongmin
 * @date 2025/11/27 14:48
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String PASSWORD = "root";

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.debug("<loadUserByUsername> 获取用户{}的信息", userName);
        if (!"root".equals(userName) && !"normal".equals(userName)) {
            log.debug("<loadUserByUsername> 用户{}不存在", userName);
            throw new UsernameNotFoundException(userName + "用户不存在");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<GrantedAuthority> list = new ArrayList<>();
        if ("root".equals(userName)) {
            list.add(new SimpleGrantedAuthority("ROLE_root"));
        } else {
            list.add(new SimpleGrantedAuthority("ROLE_normal"));
        }

        User user = new User(userName, encoder.encode(PASSWORD), list);
        return user;
    }
}
