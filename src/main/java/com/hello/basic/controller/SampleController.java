package com.hello.basic.controller;

import com.hello.basic.dto.SampleDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.*;

@Controller     // 컨트롤러 역할을 하는 클래스다~ 명시
@Slf4j          // 로그 찍기 위한 롬복 어노테이션, log 객체 바로 사용가능
@RequestMapping("/sample") // localhost:8080/sample/...
public class SampleController {
    // url 요청 주소와 주소에 해당하는 처리를 메서드로 작성
    //@RequestMapping(value="/sample") // GET,POST 둘다 처리 가능
    // GET localhost:8080/sample/ex01 요청 처리
    @RequestMapping(value="/ex01", method = RequestMethod.GET) // GET 요청만 처리
    public void basic() {
        log.info("basic 메서드 호출!!!!!!"); // 롬복의 로그객체로 로그 찍기
        System.out.println("basic 메서드 호출~~~~~~"); // 출력문으로 로그
    }

    @GetMapping("/ex02")
    public String ex02() {
        log.info("--------------------------- ex02요청!!!");
        return "sample/home"; // 브라우저에 응답해줄 templates/폴더 안의 html 파일이름
    }

    @GetMapping("/form")
    public String form() {
        log.info("-------------- form 요청!");
        return "sample/form";  // 화면에 응답해서 보여줄 html 경로 지정
    }
    /* ...:8080/sample/pro?id=java&pw=1111
    // HttpServletRequest request : 사용자 요청과 관련된 정보를 담고 있는 객체
    // HttpServletResponse response : 사용자에게 해줄 응답과 관련된 정보를 담고 있는 객체
    @GetMapping("/pro") // 넘어오는 데이터 눈으로 보기 위해 일단 GET 방식 -> POST 변경할 것임
    public String pro(HttpServletRequest request, HttpServletResponse response) {
        log.info("------------ pro 요청!");
        // 넘어온 파라미터(데이터) 꺼내기
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        log.info("------- id = {}", id);
        log.info("------- pw = {}", pw);
        return "sample/home"; // 일단 home으로 이동
    }
    //...:8080/sample/pro?id=java&pw=1234
    @GetMapping("/pro") // 넘어오는 데이터 눈으로 보기 위해 일단 GET 방식 -> POST 변경할 것임
    //public String pro(String id, int pw) { // 어노테이션 생략 + 파라미터명과 매개변수명을 일치
    public String pro(@RequestParam("id") String memberId,
                      @RequestParam("pw") int memberPw) {
        log.info("------------ pro 요청!");
        log.info("------- memberId = {}", memberId);
        log.info("------- memberPw = {}", memberPw + 1);
        return "sample/home"; // 일단 home으로 이동
    }

    // 리스트 : ArrayList 는 @RequestParam을 붙혀야함
    // 배열 : @RequestParam 생략 가능
    //:8080/sample/pro?id=java&pw=1111&hobby=football&hobby=swimming
    @GetMapping("/pro") // 넘어오는 데이터 눈으로 보기 위해 일단 GET 방식 -> POST 변경할 것임
    public String pro(String id, String pw,
                      //@RequestParam("hobby") ArrayList<String> hobby) {
                      String[] hobby) {
        log.info("------------ pro 요청!");
        log.info("------- id = {}", id);
        log.info("------- pw = {}", pw);
        log.info("------- hobby = {}", Arrays.toString(hobby));
        // Arrays.toString() : 배열 for문 안돌리고, ArrayList처럼 예쁘게 출력하기 위해

        return "sample/home"; // 일단 home으로 이동
    }*/
    // 객체로 수집
    // ..8080/sample/pro?id=java&pw=1111&hobby=netflix&hobby=football
    @PostMapping("/pro") // POST 변경
    public String pro(@ModelAttribute("sampleDTO") SampleDTO sampleDTO, RedirectAttributes rttr) {
        log.info("------------ pro 요청!");
        log.info("------------ sampleDTO = {}", sampleDTO);

        // 리다이렉트되는 페이지까지 데이터 전달 : URL 통해서 전달 ?result=success
        //rttr.addAttribute("result", "success");
        rttr.addFlashAttribute("result", "success"); // 1회성으로 데이터 전송
        //return "sample/home"; // 일단 home으로 이동
        // main 페이지로 리다이렉트 : -> 아래 @GetMapping("/main")를 브라우저에서 요청하듯 코드로 요청!
        return "redirect:/sample/main"; // /sample/main?result=success
    }

    @GetMapping("/main")
    public String main() {
        log.info("--------- main 요청!!!");
        return "sample/main"; // html 경로
    }

    @GetMapping("/ex03")
    public String ex03(Model model) {
        log.info("------------- ex03 요청!!!");
        // home html에 데이터 심어서 보내기
        model.addAttribute("txt", "abcd"); // 문자열 보내기
        model.addAttribute("num", 100); // 숫자 보내기
        model.addAttribute("data", null); // null 보내기
        model.addAttribute("age", "20");
        model.addAttribute("day", new Date()); // 객체 보내기
        model.addAttribute("arr", new int[]{1,2,3,4,5}); // int타입 배열 보내기
        model.addAttribute("list", Arrays.asList(10,20,30));
        // 직접 DTO 생성 (화면에 보내줄 샘플 DTO) -> 직접 model통해 데이터 보내기
        SampleDTO dto = new SampleDTO();
        dto.setId("hello");
        dto.setPw("1111");
        //dto.setHobby((ArrayList<String>)Arrays.asList("netflix", "football"));
        model.addAttribute("dto", dto);

        return "sample/home"; // sample 폴더의 home.html 페이지로 응답
    }

    // 내부 클래스(중첩클래스, nested class) : 클래스 안에 클래스 만들기.
    // (주로 외부 SampleController와 밀접하게 안에서만 사용하는 클래스)
    @Getter @Setter @ToString
    @AllArgsConstructor // 모든 인스턴스 변수를 매개변수로 갖는 생성자 자동 생성
    static class User {
        private String username;
        private int age;
    }

    @GetMapping("/thyme")
    public String thymeleafEx(Model model) {
        // view(html)에 데이터 전달
        //model.addAttribute("data", "Hello <b>Thymeleaf</b>");
        // 객체 : view에 User타입 객체 두개 전달
        User a = new User("userA", 10);
        User b = new User("userB", 20);
        model.addAttribute("userA", a);
        model.addAttribute("userB", b);

        // List 전달
        List<User> list = new ArrayList<>();
        list.add(a); // list에 User객체 저장
        list.add(b);
        model.addAttribute("list", list);

        // Map 전달
        Map<String, User> map = new HashMap<>();
        map.put("userA", a); // map에 (key, value) 형태로 User객체 저장
        map.put("userB", b);
        model.addAttribute("map", map);

        return "sample/thyme01"; // html 경로 리턴 (view로 응답)
    }

    @GetMapping("/thyme2")
    public String thymeleaf2(Model model) {
        // 컨트롤러에서 view 에 데이터 보내기 : 현재 날짜/시간을 갖는 LocalDateTime 객체
        model.addAttribute("localDateTime", LocalDateTime.now());
        // a 태그에 파라미터로 걸어줄 데이터 View 에 전달
        model.addAttribute("param1", "hello");
        model.addAttribute("param2", "thymeleaf");
        // 리터럴 테스트위한 데이터 전달
        model.addAttribute("data", "spring!");
        // elvis 연산자 위한 데이터 전달
        model.addAttribute("nullData", null);

        return "sample/thyme02";
    }

    @GetMapping("/ex04/{id}") // 예전개발방식 : ...8080/ex04?id=hello / RESTful 개발방식 ..8080/ex04/hello
    // @PathVariable : 경로 상의 데이터를 매개변수로 꺼내기
    public String ex04(@PathVariable("id") String id) {
        log.info("------------------- id : {}", id);
        return "sample/main";
    }

    @GetMapping("/thyme3") // url 주소 요청하면,
    public String thymeleaf3() {

        return "sample/thyme03"; // 이 경로의 html 파일 응답 (...resources/templates/ 기본 템플릿 경로로 내부에 설정)
    }

    //@GetMapping("/thyme4")
    @GetMapping("/thyme5")
    public String thymeleaf4(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("list", list);

        // 자바스크립트 inline 테스트용 데이터
        model.addAttribute("user", new User("myuser", 15));

        return "sample/thyme05";
    }



}//class
