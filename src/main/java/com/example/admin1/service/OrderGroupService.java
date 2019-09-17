package com.example.admin1.service;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.OrderGroup;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.OrderGroupRequest;
import com.example.admin1.model.network.response.OrderGroupResponse;
import com.example.admin1.repository.OrderGroupRepository;
import com.example.admin1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


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
                .revAddress(orderGroupRequest.getRevAddress())
                .revName(orderGroupRequest.getRevName())
                .paymentType(orderGroupRequest.getPaymentType())
                .totalPrice(orderGroupRequest.getTotalPrice())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(orderGroupRequest.getId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);


        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<OrderGroupResponse> update(Header<OrderGroupRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<OrderGroupResponse> response(OrderGroup orderGroup){
        OrderGroupResponse orderGroupResponse = OrderGroupResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
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
