package com.example.admin1.service;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.Item;
import com.example.admin1.model.User;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.ItemRequest;
import com.example.admin1.model.network.response.ItemResponse;
import com.example.admin1.repository.ItemRepository;
import com.example.admin1.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ItemService implements CrudInterface<ItemRequest, ItemResponse> {
    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemResponse> create(Header<ItemRequest> request) {
        ItemRequest itemRequest = request.getData();
        Item item = Item.builder()
                .status(itemRequest.getStatus())
                .name(itemRequest.getName())
                .title(itemRequest.getTitle())
                .content(itemRequest.getContent())
                .price(itemRequest.getPrice())
                .brandName(itemRequest.getBrandName())
                .registeredAt(itemRequest.getRegisteredAt())
                .partner(partnerRepository.getOne(itemRequest.getPartnerId()))
                .build();
        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemResponse> read(Long id) {

        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElse(Header.ERROR("데이터없음"));
    }

    @Override
    public Header<ItemResponse> update(Header<ItemRequest> request) {

        ItemRequest itemRequest = request.getData();
        Optional<Item> optional = itemRepository.findById(itemRequest.getId());
        return optional.map(item -> {
            item.setStatus(itemRequest.getStatus())
                    .setName(itemRequest.getName())
                    .setTitle(itemRequest.getTitle())
                    .setContent(itemRequest.getContent())
                    .setPrice(itemRequest.getPrice())
                    .setBrandName(itemRequest.getBrandName())
                    .setRegisteredAt(itemRequest.getRegisteredAt())
                    .setUnregisteredAt(itemRequest.getUnregisteredAt());
            return item;
        })
                .map(item -> itemRepository.save(item))
                .map(item -> response(item))
                .orElseGet(
                        ()->Header.ERROR("데이터없음"));
    }


    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터없음"));

    }

    private Header<ItemResponse> response(Item item){
        ItemResponse itemResponse = ItemResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(itemResponse);
    }
}
