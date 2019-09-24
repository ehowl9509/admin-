package com.example.admin1.service;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.OrderDetail;
import com.example.admin1.model.OrderGroup;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.OrderGroupRequest;
import com.example.admin1.model.network.response.OrderGroupResponse;
import com.example.admin1.repository.OrderGroupRepository;
import com.example.admin1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class OrderGroupService implements CrudInterface<OrderGroupRequest, OrderGroupResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupResponse> create(Header<OrderGroupRequest> request) {

        OrderGroupRequest orderGroupRequest = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .status(orderGroupRequest.getStatus())
                .orderType(orderGroupRequest.getOrderType())
                .revAddress(orderGroupRequest.getRevAddress())
                .revName(orderGroupRequest.getRevName())
                .paymentType(orderGroupRequest.getPaymentType())
                .totalPrice(orderGroupRequest.getTotalPrice())
                .totalQuantity(orderGroupRequest.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(orderGroupRequest.getUserId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);


        return response(newOrderGroup);
    }


    @Override
    public Header<OrderGroupResponse> read(Long id) {

        return orderGroupRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }



    @Override
    public Header<OrderGroupResponse> update(Header<OrderGroupRequest> request) {

        OrderGroupRequest orderGroupRequest = request.getData();
        Optional<OrderGroup> optional = orderGroupRepository.findById(orderGroupRequest.getId());
        return optional.map(orderGroup -> {
            orderGroup.setStatus(orderGroupRequest.getStatus())
                    .setOrderType(orderGroupRequest.getOrderType())
                    .setRevAddress(orderGroupRequest.getRevAddress())
                    .setRevName(orderGroupRequest.getRevName())
                    .setPaymentType(orderGroupRequest.getPaymentType())
                    .setTotalPrice(orderGroupRequest.getTotalPrice())
                    .setTotalQuantity(orderGroupRequest.getTotalQuantity())
                    .setOrderAt(orderGroupRequest.getOrderAt())
                    .setArrivalDate(orderGroupRequest.getArrivalDate())
                    .setUser(userRepository.getOne(orderGroupRequest.getId()));
            return orderGroup;
        })
                .map(orderGroup -> orderGroupRepository.save(orderGroup))
                .map(orderGroup -> response(orderGroup))
                .orElseGet(
                        () -> Header.ERROR("데이터없음"));

    }

    @Override
    public Header delete(Long id) {

        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터없음"));

    }


    private Header<OrderGroupResponse> response(OrderGroup orderGroup) {

        OrderGroupResponse orderGroupResponse = OrderGroupResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(orderGroupResponse);
    }
}