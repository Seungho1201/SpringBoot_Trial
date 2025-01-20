package com.seungho.shop.Member;

import com.seungho.shop.Item.Item;
import lombok.RequiredArgsConstructor;
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
    public String signIn() {
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
}
