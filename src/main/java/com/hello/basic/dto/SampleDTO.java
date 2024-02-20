package com.hello.basic.dto;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor // 선택한(값 필수) 변수만 매개변수로 지정한 생성자 생성
        // final 붙은 변수, @NonNull 어노테이션이 붙은 변수 만 매개변수로
public class SampleDTO {
    // 변수이름을 넘어오는 데이터의 파라미터명과 동일하게 작성
    private String id;
    private String pw;
    private ArrayList<String> hobby = new ArrayList<>();

}
