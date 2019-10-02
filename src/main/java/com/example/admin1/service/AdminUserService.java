package com.example.admin1.service;


import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.AdminUser;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.AdminUserRequest;
import com.example.admin1.model.network.response.AdminUserResponse;
import com.example.admin1.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminUserService implements CrudInterface<AdminUserRequest, AdminUserResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;


    @Override
    public Header<AdminUserResponse> create(Header<AdminUserRequest> request) {
        AdminUserRequest adminUserRequest = request.getData();
        AdminUser adminUser = AdminUser.builder()
                .account(adminUserRequest.getAccount())
                .password(adminUserRequest.getPassword())
                .status(adminUserRequest.getStatus())
                .role(adminUserRequest.getRole())
                .lastLoginAt(LocalDateTime.now())
                .registeredAt(LocalDateTime.now())
                .build();
        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserResponse> read(Long id) {
        return adminUserRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header<AdminUserResponse> update(Header<AdminUserRequest> request) {
        AdminUserRequest adminUserRequest = request.getData();
        Optional<AdminUser> optional = adminUserRepository.findById(adminUserRequest.getId());
        return optional.map(adminUser -> {
            adminUser.setAccount(adminUserRequest.getAccount())
                    .setPassword(adminUserRequest.getPassword())
                    .setStatus(adminUserRequest.getStatus())
                    .setRole(adminUserRequest.getRole())
                    .setLoginFailCount(adminUserRequest.getLoginFailCount());
            return adminUser;
        })
                .map(adminUser -> adminUserRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<AdminUserResponse> response(AdminUser adminUser) {
        AdminUserResponse adminUserResponse = AdminUserResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .registeredAt(adminUser.getRegisteredAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .build();
        return Header.OK(adminUserResponse);

    }
}
