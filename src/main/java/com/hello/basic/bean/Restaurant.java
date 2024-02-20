package com.hello.basic.bean;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Chef를 주입
// Restaurant도 스프링이 관리하는 빈이 되어야 한다.
@Component   // 스프링에게 해당 클래스가 스프링에서 관리되어야하는 대상임을 표시.
// 스프링에서 component-scan 하면서 어노테이션을 발견하고, 스프링 빈으로 등록(객체 생성해서 관리)
// 스프링 빈으로 등록된 스프링빈(객체)은 싱글턴 방식으로 관리가 된다. (빌려다 쓰는 -> 주입받아 사용)
//  참고. DTO, VO 는 스프링빈으로 등록하지 않는다. (싱글턴이 아닌 자신만의 공간에 각 데이터를 저장해야하기 때문)
// 의존성 자동 주입을 위해서는 스프링빈으로 관리되어야 함.
// @Component <- @Controller, @Service, @Repository
@Data
// #2-2. 생성자 : 롬복 이용하여 생성
//@RequiredArgsConstructor // @NonNull, final 변수만 매개변수로 갖는 생성자 자동 생성
public class Restaurant {

    //@Autowired  // #1. 자동 주입
    //#2-2.@NonNull
    //private final Chef chef;
    //#3. setter로 주입받기 (Restaurant의 chef set메서드를 통해 주입 받기)
    @Setter(onMethod_= @Autowired)
    private Chef chef;

    /* #2-1. 생성자 : 직접 작성
    public Restaurant(Chef chef) {
        this.chef = chef;
    }*/

}
