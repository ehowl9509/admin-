package com.example.admin1.controller.api;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.AdminUserRequest;
import com.example.admin1.model.network.response.AdminUserResponse;
import com.example.admin1.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserController implements CrudInterface<AdminUserRequest, AdminUserResponse>{

    @Autowired
    private AdminUserService adminUserService;

    @Override
    @PostMapping("")
    public Header<AdminUserResponse> create(@RequestBody Header<AdminUserRequest> request) {
        return adminUserService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminUserResponse> read(@PathVariable Long id) {
        return adminUserService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<AdminUserResponse> update(@RequestBody Header<AdminUserRequest> request) {
        return adminUserService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return adminUserService.delete(id);
    }
}
