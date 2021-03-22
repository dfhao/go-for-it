package com.dfhao.ispringboot.test.controller;

import com.dfhao.ispringboot.test.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试程序
 *
 * @author :  dfhao
 * @date :  2021/3/19 10:50
 */
@RestController
public class TestController {
    @Value("${com.dfhao.title}")
    private String title;
    @Value("${com.dfhao.description}")
    private String description;

    @GetMapping("/getHello")
    public String getHello() {
        return "hello world";
    }

    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setUserName(title);
        user.setPassWord(description);
        return user;
    }
}
