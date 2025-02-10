package com.seungho.shop.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Entity 어노테이션 붙이고 테이블 형태 구성 바로 가능
@Entity
@ToString   // Object 안에 있는 모든 데이터를 출력해주는 Lombok 라이브러리
@Getter
@Setter
@Table(indexes = @Index(columnList = "title", name = "작명"))    // 지정한 컬럼에서 인덱스로 정렬해놓음
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String title;
    private Integer price;


}

