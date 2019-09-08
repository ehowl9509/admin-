package com.example.admin1.controller;


import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.UserRequest;
import com.example.admin1.model.network.response.UserResponse;
import com.example.admin1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping
    public Header<UserResponse> create(@RequestBody Header<UserRequest> request) {
        log.info("{}", request);
        return userService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserResponse> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return userService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserResponse> update(@RequestBody Header<UserRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
