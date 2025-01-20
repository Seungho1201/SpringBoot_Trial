package com.seungho.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*
     SpringSecurity가 유저가 제출한 비번, DB에 있던 비번 자동으로 검사해줌
     근데 SpringSecurity는 "DB에 있던 비번" <= 이게 어디에 있는지 모름
     그거 작성하는 곳임
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    DB에서 username을 가진 유저를 찾아와서
     //   return new User(유저아이디, 비번, 권한) 해주세요

        var result = memberRepository.findByUsername(username);

        if(result.isEmpty()) {
            // 없으면 오류 발생시키자
            throw new UsernameNotFoundException("그런 아이디 없음");
        }

        var user = result.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반유저"));    // 메모일 뿐임   // 나중에 API에서 현재 유저의 권한 출력가능


        var a = new CustomUser(user.getUsername(), user.getPassword(),authorities );
        a.displayName = user.getDisplayName();

        return a;
    }

}

// User 클래스를 커스터마이징 해보자
class CustomUser extends User {

    public String displayName;

    public CustomUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }
}
