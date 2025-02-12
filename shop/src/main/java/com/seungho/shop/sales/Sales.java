package com.seungho.shop.sales;


import com.seungho.shop.Member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Integer price;
    private Integer count;

    // 항상 member 테이블이 필요한건 아님
    // @ManyToOne 은 테이블 내용을 다 가져옴 (성능 이슈)
    // fetch = FetchType.LAZY 게으르게 필요할때만 데이터 가져와움
    // JPA에서 이거 쓰라고 권장 함
    // 반대로 EAGER는 항상 가져오는거
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private Member member;

    // 행 추가시 현재시간 자동으로 채워짐
    @CreationTimestamp
    private LocalDateTime created;
}
