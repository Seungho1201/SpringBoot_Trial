package com.seungho.shop.Member;

import com.seungho.shop.Item.Item;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String signIn(Authentication auth) {

        // 로그인 중인 유저가 /register 접속시 /list로 이동
        if(auth != null) {
            return "redirect:/list";
        }

        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(String username, String password, String displayName) {

        Member member = new Member();

        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setDisplayName(displayName);

        memberRepository.save(member);

        return "redirect:/list";
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }


    // 마이 페이지 이동
    @GetMapping("/mypage")
    // Authentication auth 안에 현재 로그인한 정보 그대로 담겨있음
    public String myPage(Authentication auth) {

        // Spring Security에선 타입 캐스팅 권장
        // CustomUser result = (CustomUser)auth.getPrincipal();
        // System.out.println(result.displayName);

        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDTO getUser(){
        var a = memberRepository.findById(1L);
        var result = a.get();

        var map = new HashMap();

        var data = new MemberDTO(result.getUsername(), result.getDisplayName());

        //data.username = result.getUsername();
        //data.displayname = result.getDisplayName();

        return data;
    }

    //보내주는 데이터를 맘대로 추가하고 그러면 프론트에서 고장날 수 있음
    // 새로운 api를 만들자
    @GetMapping("/v2/user/1")
    @ResponseBody
    public MemberDTO getUser2(){
        var a2 = memberRepository.findById(1L);
        var result2 = a2.get();

        var data2 = new MemberDTO(result2.getUsername(), result2.getDisplayName(), result2.getId());


        return data2;
    }

    // Data Transfer Object (데이터 변환용 오브젝트)
    // 비밀번호를 보내줄순없잖아
    class MemberDTO{

        // public 있어야 JSON 타입으로 변환 가능
        // @Getter도 가능 하
        public String username;
        public String displayname;
        public Long id;

        // 생성자로 박아버리면 편하겟지?
        MemberDTO(String username, String displayname){
            this.username = username;
            this.displayname = displayname;
        }

        MemberDTO(String username, String displayname, Long id){
            this.username = username;
            this.displayname = displayname;
            this.id = id;
        }
    }
}
