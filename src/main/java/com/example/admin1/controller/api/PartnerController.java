package com.example.admin1.controller.api;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.PartnerRequest;
import com.example.admin1.model.network.response.PartnerResponse;
import com.example.admin1.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
public class PartnerController implements CrudInterface<PartnerRequest, PartnerResponse> {
    @Autowired
    private PartnerService partnerService;

    @Override
    @PostMapping("")
    public Header<PartnerResponse> create(@RequestBody Header<PartnerRequest> request) {
         return partnerService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerResponse> read(@PathVariable Long id) {
        return partnerService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerResponse> update(@RequestBody Header<PartnerRequest> request) {
        return partnerService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return partnerService.delete(id);
    }
}
