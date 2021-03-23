package com.dfhao.ispringboot.test.dao;

import com.dfhao.ispringboot.test.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * 测试程序
 *
 * @author :  dfhao
 * @date :  2021/3/23 17:50
 */
@Component("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);
}
