package com.seungho.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    // 인터페이스를 원하는 클래스에 Repository 등록
    // itemRepository 이 안에 DB 입출력 함수 잔뜩 들어있음
    // 사실상 Lombok임
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        Model items = model.addAttribute("items", result);

        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@ModelAttribute Item item) { // Item 클래스의 오브젝트를 하나 뽑아서 바로 넣어줌
        itemRepository.save(item);

        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        Optional<Item> result = itemRepository.findById(id);

        if(result.isPresent()) {
            model.addAttribute("item", result.get());
        } else {
            return "redirect:/list";
        }

        return "detail.html";
    }

}
