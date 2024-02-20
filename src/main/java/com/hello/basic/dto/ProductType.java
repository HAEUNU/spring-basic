package com.hello.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    PHONE("핸드폰"), COMPUTER("컴퓨터"), TV("Tv"), ETC("기타");
    private final String description;  // value
}
