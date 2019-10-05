package com.example.admin1.model.enumd;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {

    ALL(0, "묶음", "묵음배송"),
    EACH(1, "개별", "개별배송")
    ;

    private Integer id;
    private String title;
    private String description;
}
