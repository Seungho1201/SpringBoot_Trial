package com.seungho.shop.Item;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Item, Long>  테이블 이름과 기본키가 되는 테이블의 데이터 타입 가져온거임
public interface ItemRepository extends JpaRepository<Item, Long> {

}
