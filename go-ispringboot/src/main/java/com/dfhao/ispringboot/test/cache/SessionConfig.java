package com.dfhao.ispringboot.test.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 共享session
 *
 * @author :  dfhao
 * @date :  2021/3/19 10:50
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 15)
public class SessionConfig {
}
