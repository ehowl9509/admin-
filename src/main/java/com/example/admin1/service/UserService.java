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
import java.util.Optional;

@Service
public class UserService implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserResponse> create(Header<UserRequest> request) {

        UserRequest userRequest = request.getData();

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

        UserRequest userRequest = request.getData();

        Optional<User> optional = userRepository.findById(userRequest.getId());

        return optional.map(user -> {

            user.setAccount(userRequest.getAccount())
                    .setPassword(userRequest.getAccount())
                    .setStatus(userRequest.getStatus())
                    .setPhoneNumber(userRequest.getPhoneNumber())
                    .setEmail(userRequest.getEmail())
                    .setRegisteredAt(userRequest.getRegisteredAt())
                    .setUnregisteredAt(userRequest.getUnregisteredAt());
            return user;
        })
                .map(user -> userRepository.save(user))
                .map(user -> response(user))
                .orElseGet(
                        ()->Header.ERROR("데이터없음"));
    }



    @Override
    public Header delete(Long id) {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터없음"));
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
