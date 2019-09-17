package com.example.admin1.controller.api;


import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.OrderGroupRequest;
import com.example.admin1.model.network.response.OrderGroupResponse;
import com.example.admin1.service.OrderGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/OrderGroup")
public class OrderGroupController implements CrudInterface<OrderGroupRequest, OrderGroupResponse> {

    @Autowired
    private OrderGroupService orderGroupService;

    @PostMapping("")
    @Override
    public Header<OrderGroupResponse> create(@RequestBody Header<OrderGroupRequest> request) {
        return orderGroupService.create(request);
    }

    @GetMapping("{id}")
    @Override
    public Header<OrderGroupResponse> read(@PathVariable Long id) {
        return orderGroupService.read(id);
    }

    @PutMapping("")
    @Override
    public Header<OrderGroupResponse> update(@RequestBody Header<OrderGroupRequest> request) {
        return orderGroupService.update(request);
    }

    @DeleteMapping("{id}")
    @Override
    public Header delete(@PathVariable Long id) {
        return orderGroupService.delete(id);
    }
}
