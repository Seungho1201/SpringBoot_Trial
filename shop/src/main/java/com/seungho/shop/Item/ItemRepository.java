package com.seungho.shop.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// JpaRepository<Item, Long>  테이블 이름과 기본키가 되는 테이블의 데이터 타입 가져온거임
public interface ItemRepository extends JpaRepository<Item, Long> {

    // 테이블에서 X개만 가져오는 함수 임
    Page<Item> findPageBy(Pageable page);

    List<Item> findAllByTitleContains(String title);

    // 직접 sql 쿼리문을 쓰자
    //@Query(value = "select * from item where id = ?1;", nativeQuery = true)
    //Item rawQuery1(Long num);

    // full text index로 검색하기
    // match ~ against 쓰면 검색 순위 자동 정렬해줌
    @Query(value = "select * from item where match(title) against(?1);", nativeQuery = true)
    List<Item> rawQuery1(String text);
}
