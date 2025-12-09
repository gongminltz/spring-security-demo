package com.gm.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author gongmin
 * @date 2025/11/28 9:12
 */
@RestController
public class DemoController {
    @GetMapping("/login")
    public String loginPage() {
        return "<html><body>"
                + "<form method='post' action='/login'>"
                + "用户：<input name='username'/><br/>"
                + "密码：<input name='password' type='password'/><br/>"
                + "<button type='submit'>登录</button>"
                + "</form></body></html>";
    }

    @GetMapping("/user/root")
    public String root() {
        return "root 页面";
    }

    @GetMapping("/user/normal")
    public String normal() {
        return "normal 页面";
    }

    @GetMapping("/index")
    public String index() {
        return "首页";
    }
}
