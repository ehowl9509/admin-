package com.example.admin1.service;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.Category;
import com.example.admin1.model.Partner;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.PartnerRequest;
import com.example.admin1.model.network.response.PartnerResponse;
import com.example.admin1.repository.CategoryRepository;
import com.example.admin1.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PartnerService implements CrudInterface<PartnerRequest, PartnerResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Header<PartnerResponse> create(Header<PartnerRequest> request) {
        PartnerRequest partnerRequest = request.getData();
        Partner partner = Partner.builder()
                .name(partnerRequest.getName())
                .status(partnerRequest.getStatus())
                .address(partnerRequest.getAddress())
                .callCenter(partnerRequest.getCallCenter())
                .partnerNumber(partnerRequest.getPartnerNumber())
                .businessNumber(partnerRequest.getBusinessNumber())
                .ceoName(partnerRequest.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getOne(partnerRequest.getCategoryId()))
                .build();

        Partner newPartner = partnerRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerResponse> read(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> response(partner))
                .orElse(Header.ERROR("데이터없음"));
    }

    @Override
    public Header<PartnerResponse> update(Header<PartnerRequest> request) {
        PartnerRequest partnerRequest = request.getData();
        Optional<Partner> optional = partnerRepository.findById(partnerRequest.getId());
        return optional.map(partner -> {
            partner.setName(partnerRequest.getName())
                    .setStatus(partnerRequest.getStatus())
                    .setAddress(partnerRequest.getAddress())
                    .setCallCenter(partnerRequest.getCallCenter())
                    .setPartnerNumber(partnerRequest.getPartnerNumber())
                    .setBusinessNumber(partnerRequest.getBusinessNumber())
                    .setCeoName(partnerRequest.getCeoName());
            return partner;
        })
                .map(partner -> partnerRepository.save(partner))
                .map(partner -> response(partner))
                .orElse(Header.ERROR("데이터없음"));
    }


    @Override
    public Header delete(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> {
                    partnerRepository.delete(partner);
                    return Header.OK();
                })
                .orElse(Header.ERROR("데이터없음"));
    }

    private Header<PartnerResponse> response(Partner partner){
        PartnerResponse partnerResponse = PartnerResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();
        return Header.OK(partnerResponse);
    }
}
