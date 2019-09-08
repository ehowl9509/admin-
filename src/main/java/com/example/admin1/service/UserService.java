package com.example.admin1.service;


import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.User;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.UserRequest;
import com.example.admin1.model.network.response.UserResponse;
import com.example.admin1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserResponse> create(Header<UserRequest> request) {

        UserRequest userRequest = request.getDate();

        User user = User.builder()
                .account(userRequest.getAccount())
                .password(userRequest.getPassword())
                .status("REGISTERD")
                .phoneNumber(userRequest.getPhoneNumber())
                .email(userRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        return response(newUser);
    }


    @Override
    public Header<UserResponse> read(Long id) {

        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }



    @Override
    public Header<UserResponse> update(Header<UserRequest> request) {

        return null;
    }



    @Override
    public Header delete(Long id) {

        return null;
    }





    private Header<UserResponse> response(User user){
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        return Header.OK(userResponse);
    }
}
