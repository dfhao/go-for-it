package com.dfhao.ispringboot.test.dao;

import com.dfhao.ispringboot.IspringbootApplication;
import com.dfhao.ispringboot.test.bean.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

/**
 * 测试程序
 *
 * @author :  dfhao
 * @date :  2021/3/23 17:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IspringbootApplication.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", date));
        userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456", date));
        userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456", date));

        Assert.assertEquals(9, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa1"));
    }
}
