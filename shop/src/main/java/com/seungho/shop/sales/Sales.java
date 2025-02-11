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

    @ManyToOne
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Member member;

    // 행 추가시 현재시간 자동으로 채워짐
    @CreationTimestamp
    private LocalDateTime created;
}
