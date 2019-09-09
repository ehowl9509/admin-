package com.example.admin1.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

    //응답시간
    private LocalDateTime transactionTime;

    //응답코드
    private String resultCode;

    //부가설명
    private String description;

    private T data;


    //프론트  status 확인
    public static <T> Header<T> OK(){
        return (Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build());
    }
    //데이터 부분
    public static <T> Header<T> OK(T data){
        return (Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build());
    }
    //에러
    public static <T> Header<T> ERROR(String description){
        return (Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build());
    }
}
