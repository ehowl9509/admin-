package com.example.admin1.repository;

import com.example.admin1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumberOrderByIdDesc(String phoneNumber);
}
