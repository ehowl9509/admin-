package com.example.admin1.controller.api;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.PartnerRequest;
import com.example.admin1.model.network.response.PartnerResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
public class PartnerController implements CrudInterface<PartnerRequest, PartnerResponse> {

    @Override
    @PostMapping("")
    public Header<PartnerResponse> create(@RequestBody Header<PartnerRequest> request) {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<PartnerResponse> update(@RequestBody Header<PartnerRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
