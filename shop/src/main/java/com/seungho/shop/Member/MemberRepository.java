package com.seungho.shop.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Drived Query methods

    // 찾은거 Member 타입으로 바꿔줌
    // findAllByUsername 처럼 All 붙이면 일치하는 행 전부 찾아옴
    Optional<Member> findByUsername(String username);
}
