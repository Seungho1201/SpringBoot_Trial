package com.seungho.shop.Member;

import com.seungho.shop.Item.Item;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/login")
    public String login() {
        System.out.println(memberRepository.findByUsername("11").get().getDisplayName());
        return "login.html";
    }

    @GetMapping("/mypage")
    // Authentication auth 안에 현재 로그인한 정보 그대로 담겨있음
    public String myPage(Authentication auth) {

        // Spring Security에선 타입 캐스팅 권장
        CustomUser result = (CustomUser)auth.getPrincipal();
        System.out.println(result.displayName);

        return "mypage.html";
    }
}
