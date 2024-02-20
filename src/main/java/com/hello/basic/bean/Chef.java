package com.hello.basic.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component // 스프링빈으로 등록 (스프링이 필요한곳에 이 객체를 주입시킬 수 있음)
@Data // setter, getter, toString, 생성자 등 자동으로 생성
public class Chef {
}
