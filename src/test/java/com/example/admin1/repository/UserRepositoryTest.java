package com.example.admin1.repository;

import com.example.admin1.Admin1ApplicationTests;
import com.example.admin1.model.OrderDetail;
import com.example.admin1.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class UserRepositoryTest extends Admin1ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void create(){
        String account = "Test04";
        String password = "password";
        String status = "nono";
        String email = "ehowl9509@naver.com";
        String phoneNumber = "010-4921-9509";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "adminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User newuser = userRepository.save(user);

        assertNotNull(newuser);
    }


}