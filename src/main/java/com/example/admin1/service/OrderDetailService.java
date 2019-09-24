package com.example.admin1.service;


import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.OrderDetail;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.OrderDetailRequest;
import com.example.admin1.model.network.response.OrderDetailResponse;
import com.example.admin1.repository.ItemRepository;
import com.example.admin1.repository.OrderDetailRepository;
import com.example.admin1.repository.OrderGroupRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderDetailService implements CrudInterface<OrderDetailRequest, OrderDetailResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderGroupRepository orderGroupRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailResponse> create(Header<OrderDetailRequest> request) {

        OrderDetailRequest orderDetailRequest = request.getData();
        OrderDetail body = OrderDetail.builder()
                .status(orderDetailRequest.getStatus())
                .arrivalDate(LocalDateTime.now())
                .totalPrice(orderDetailRequest.getTotalPrice())
                .quantity(orderDetailRequest.getQuantity())
                .orderGroup(orderGroupRepository.getOne(orderDetailRequest.getOrderGroupId()))
                .item(itemRepository.getOne(orderDetailRequest.getItemId()))
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(body);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailResponse> read(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }


    @Override
    public Header<OrderDetailResponse> update(Header<OrderDetailRequest> request) {
        OrderDetailRequest orderDetailRequest = request.getData();
        Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailRequest.getId());
        return optional.map(orderDetail -> {
            orderDetail.setStatus(orderDetailRequest.getStatus())
                    .setArrivalDate(orderDetailRequest.getArrivalDate())
                    .setTotalPrice(orderDetailRequest.getTotalPrice())
                    .setQuantity(orderDetailRequest.getQuantity())
                    .setOrderGroup(orderGroupRepository.getOne(orderDetailRequest.getOrderGroupId()))
                    .setItem(itemRepository.getOne(orderDetailRequest.getItemId()));
            return orderDetail;
        })
                .map(orderDetail -> orderDetailRepository.save(orderDetail))
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetailRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터없음"));
    }


    private Header<OrderDetailResponse> response(OrderDetail orderDetail){
        OrderDetailResponse body = OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .totalPrice(orderDetail.getTotalPrice())
                .quantity(orderDetail.getQuantity())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();

        return Header.OK(body);
    }
}
