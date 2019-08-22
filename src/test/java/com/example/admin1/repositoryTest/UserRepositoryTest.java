package com.example.admin1.repositoryTest;

import com.example.admin1.Admin1ApplicationTests;
import com.example.admin1.model.User;
import com.example.admin1.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends Admin1ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();

        user.setAccount("ehowl9509");
        user.setEmail("ehowl9509@naver.com");
        user.setPassword("9509");
        user.setPhoneNumber("010-4949-9509");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("ehowl");
        User newUser = userRepository.save(user);

        System.out.println(newUser);
    }

}
