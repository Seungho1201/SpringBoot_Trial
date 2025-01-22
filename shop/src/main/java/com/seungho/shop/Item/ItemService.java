package com.seungho.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

// 1. new Class 할 클래스에 @Service
// 2. 사용할 곳에서 변수로 등록
// 3. 변수 사용

@Service    // 어노테이션 붙여야 스프링이 Ioc Container에 요 클래스에서 오브젝트 하나 뽑아서 컨테이너 안에 넣어둠 그 뽑아준걸 Bean 이라 함
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public void listItem(Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
    }

    public Optional<Item> detailItem(Long id) {
        Optional<Item> resultItem = itemRepository.findById(id);
        return resultItem;
    }

    public void saveItem(@ModelAttribute Item item){
        itemRepository.save(item);
    }

}
