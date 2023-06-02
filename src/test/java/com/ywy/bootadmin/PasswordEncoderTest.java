package com.ywy.bootadmin;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密测试
 *
 * @author ywy
 * @date 2020-04-08 18:27
 */
public class PasswordEncoderTest {
    @Test
    public void encode() {
        String str = new BCryptPasswordEncoder().encode("123456");
        System.out.println(str);
    }
}
