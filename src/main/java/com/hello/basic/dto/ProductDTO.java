package com.hello.basic.dto;

import lombok.Data;

import java.util.List;

@Data // setter. getter, toString, 기본생성자...
public class ProductDTO {
    private String productName;
    private Integer price;
    private Integer quantity;
    private Boolean onSale; // 판매 여부
    private List<String> colors;  // multi-checkbox -> 리스트로 받기
    private ProductType productType;   // radio로 하나만 선택해 값 넘어오는것 받기 : enum 사용
    private String deliveryType; // 일반 클래스 활용
}
