package com.example.admin1.repository;

import com.example.admin1.Admin1ApplicationTests;
import com.example.admin1.model.Partner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PartnerRepositoryTest extends Admin1ApplicationTests {


    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        String name = "partner";
        String status = "nono";
        String address = "관아국";
        String callCenter = "010-9509-4941";
        String partnerNumber = "010-4941-9509";
        String businessNumber = "123451234";
        String ceoName =  "안이닌규";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "adminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
//        partner.setCategoryId(categoryId);

        Partner newpartner = partnerRepository.save(partner);
        Assert.assertNotNull(newpartner);
        Assert.assertEquals(newpartner.getName(), name);
    }

    
}