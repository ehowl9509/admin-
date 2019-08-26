package com.example.admin1.repository;

import com.example.admin1.model.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
}
