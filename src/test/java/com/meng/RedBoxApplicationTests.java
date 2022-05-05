package com.meng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class RedBoxApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("123456"));
        String s = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTE3MTY2ODEsImNyZWF0ZWQiOjE2NTE3MTQ4ODE4NzMsInVzZXJuYW1lIjoiYWRtaW4ifQ.3Xwp6YserXdfRyZpauv1OtcpOMRePwM7tAx-kJeAx0XDLhEBrTIjxRRBlRrqGbgwBFNkEtb4P-pdJO7hZ1P5qQ";
        String s1 = s.substring(7);
        System.out.println(s1);
    }

}
