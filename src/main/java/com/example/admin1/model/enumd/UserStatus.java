package com.example.admin1.model.enumd;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  UserStatus {

    RESISTERED(0,"등록", "사용자등록"),
    UNRESISTERED(1,"해제", "사용자해지")
    ;


    private Integer id;
    private String title;
    private String description;
}
