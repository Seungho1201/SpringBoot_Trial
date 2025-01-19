package com.seungho.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    // 인터페이스를 원하는 클래스에 Repository 등록
    // itemRepository 이 안에 DB 입출력 함수 잔뜩 들어있음
    // 사실상 Lombok임
    // 스프링이 오브젝트 하나 뽑아서 넣어줌
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {

        // Service 레이어 분리
        itemService.listItem(model);

        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@ModelAttribute Item item) { // Item 클래스의 오브젝트를 하나 뽑아서 바로 넣어줌

        // Service 레이어 분리
        itemService.saveItem(item);

        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        Optional<Item> result= itemService.detailItem(id);

            if(result.isPresent()) {
                model.addAttribute("item", result.get());
                return "detail.html";
            } else {
                return "redirect:/list";
            }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){

        Optional<Item> result= itemRepository.findById(id);

        if(result.isPresent()) {
            model.addAttribute("item", result.get());
        } else {
            return "redirect:/list";
        }

        return "edit.html";
    }

    @PostMapping("/editItem/{id}")
    String editItem(@PathVariable Long id, String title, int price){

        Item item = new Item();

        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);

        itemRepository.save(item);

        return "redirect:/list";
    }

    // ajax
    /*
    @PostMapping("/test1")
    String test(@RequestBody Map<String,Object> body){
        System.out.println(body.get("name"));
        return "redirect:/list";
    }

     */

    // 간단한 자료는 쿼리 스트링
    @GetMapping("/test1")
    String test(@RequestParam String name, int age){

        System.out.println(name);
        System.out.println(age);

        return "redirect:/list";
    }

    @PostMapping("/delete/{id}")
    String deleteItem(@PathVariable Long id) {

        itemRepository.deleteById(id);

        return "redirect:/list";
    }






}
