package com.dfhao.ispringboot.test.controller;

import com.dfhao.ispringboot.test.bean.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;


/**
 * 测试程序
 *
 * @author :  dfhao
 * @date :  2021/3/19 10:50
 */
@RestController
public class UserController {
    @GetMapping("/getUser")
    @Cacheable(value = "user-key")
    public User getUser() {
        User user = new User("aa@126.com", "aa", "aa123456", "aa", new Date());
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    //    共享session
    @GetMapping("/uid")
    public String uid(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

}
