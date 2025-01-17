package com.seungho.shop;

import jakarta.persistence.*;
import lombok.ToString;

// @Entity 어노테이션 붙이고 테이블 형태 구성 바로 가능
@Entity
@ToString   // Object 안에 있는 모든 데이터를 출력해주는 Lombok 라이브러리
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long id;
    public String title;
    public Integer price;


}

