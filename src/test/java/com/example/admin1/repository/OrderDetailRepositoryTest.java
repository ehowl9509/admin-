package com.example.admin1.repository;

import com.example.admin1.model.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("배송준비중");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));

    }

}