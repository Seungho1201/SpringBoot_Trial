package com.seungho.shop.Member;

import com.seungho.shop.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    private String username;
    private String displayName;
    private String password;

    // ToString 상호참조 제외
    @ToString.Exclude
    // 내 정보 뽀려가는 테이블 행들 자동 출력은 @OneToMany
    @OneToMany(mappedBy = "member")
    List<Sales> sales = new ArrayList<>();

}
