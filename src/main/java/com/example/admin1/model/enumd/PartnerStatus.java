package com.example.admin1.model.enumd;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  PartnerStatus {


    RESISTERED(0,"등록", "파트너등록"),
    UNRESISTERED(1,"해제", "파트너해제")
    ;

    private Integer id;
    private String title;
    private String description;
}
