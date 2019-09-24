package com.example.admin1.controller.api;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.OrderDetailRequest;
import com.example.admin1.model.network.response.OrderDetailResponse;
import com.example.admin1.model.network.response.OrderGroupResponse;
import com.example.admin1.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController implements CrudInterface<OrderDetailRequest, OrderDetailResponse> {


    @Autowired
    private OrderDetailService orderDetailService;


    @Override
    @PostMapping("")
    public Header<OrderDetailResponse> create(@RequestBody Header<OrderDetailRequest> request) {
        return orderDetailService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<OrderDetailResponse> read(@PathVariable Long id) {
        return orderDetailService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderDetailResponse> update(@RequestBody Header<OrderDetailRequest> request) {
        return orderDetailService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return orderDetailService.delete(id);
    }
}
