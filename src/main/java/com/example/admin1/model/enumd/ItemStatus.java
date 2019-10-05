package com.example.admin1.model.enumd;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ItemStatus {

    RESISTERED(0,"등록", "상품등록"),
    UNRESISTERED(1,"해제", "상품해제"),
    WAITING(2,"준비","상품준비중")
    ;


    private Integer id;
    private String title;
    private String description;
}
