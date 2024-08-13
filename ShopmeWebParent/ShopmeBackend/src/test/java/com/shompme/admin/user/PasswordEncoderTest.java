package com.shompme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "pankaj@123";
      String encodePassword =  passwordEncoder.encode(password);
        System.out.println(encodePassword);
        boolean matches = passwordEncoder.matches(password,encodePassword);
        assertThat(matches).isTrue();
    }
}
