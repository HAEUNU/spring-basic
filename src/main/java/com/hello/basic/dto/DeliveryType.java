package com.hello.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryType {
    private String type;   // option value로 사용 (option 선택하면 내부에서 사용할 데이터값)
    private String typeName; // option text로 사용 (사용자에게 보여줄 화면 이름)
}
