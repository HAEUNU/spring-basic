package com.hello.basic.controller;

import com.hello.basic.dto.DeliveryType;
import com.hello.basic.dto.ProductDTO;
import com.hello.basic.dto.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller // 컨트롤러 역할을 하는 스프링빈으로 등록해줘~
@RequestMapping("/sample2")
@Slf4j // 로그 찍기
public class SampleController2 {

    // 메서드 레벨에 지정하면, 현 클래스내의 모든 view에 데이터를 전달
    // 폼 화면 구성 시, 멀티 체크박스에서 사용할 데이터 보내기 (map)
    @ModelAttribute("colors")
    public Map<String, String> colors() {
        log.info("********************** colors 데이터 전송!!!");
        Map<String, String> colors = new LinkedHashMap<>(); // 입력한 순서대로 저장되도록
        colors.put("WHITE", "화이트"); // key는 checkbox value로 사용, value는 label 문구로 사용
        colors.put("BLACK", "블랙");
        colors.put("SPACEGREY", "스페이스그레이");
        return colors;
    }
    // 폼 화면 구성 시 라디오에서 사용할 데이터 보내기 (enum)
    @ModelAttribute("productType")
    public ProductType[] productTypes() {
        log.info("productType.values : {}", Arrays.toString(ProductType.values()));
        // -> productType.values : [PHONE, COMPUTER, TV, ETC]
        return ProductType.values();
    }
    // 폼 화면 구성 시, select-option에서 사용할 데이터 보내기 (객체)
    @ModelAttribute("deliveryTypes")
    public List<DeliveryType> deliveryTypes() {
        List<DeliveryType> deliveryTypes = new ArrayList<>();
        deliveryTypes.add(new DeliveryType("LOCAL", "국내배송"));
        deliveryTypes.add(new DeliveryType("INTERNATIONAL", "해외배송"));
        deliveryTypes.add(new DeliveryType("PICKUP", "방문수령"));
        return deliveryTypes;
    }


    @GetMapping("/newProduct") // 빈 DTO form페이지에 전달
    public String form(@ModelAttribute("productDTO") ProductDTO productDTO, Model model) {
        log.info("------------- newProduct form 요청!!!");
        return "sample2/form";
    }

    @PostMapping("/newProduct") // 상품 등록 처리 요청 경로
    public String process(ProductDTO productDTO) {
        log.info("productDTO : {}", productDTO);
        return "redirect:/sample/main";
    }


}
