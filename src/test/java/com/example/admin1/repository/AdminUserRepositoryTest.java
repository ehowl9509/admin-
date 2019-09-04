package com.example.admin1.repository;

import com.example.admin1.Admin1ApplicationTests;
import com.example.admin1.model.AdminUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AdminUserRepositoryTest extends Admin1ApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;


    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("admi2n");
        adminUser.setPassword("admin21");
        adminUser.setStatus("regi2");
        adminUser.setRole("part2");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("adminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        assertNotNull(newAdminUser);
    }

}