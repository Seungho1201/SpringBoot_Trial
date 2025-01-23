package com.seungho.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    String write(Authentication auth, Model model) {

        // 글 작성시 로그인 한 유저 이름도 넣어보자

        // 로그아웃 상태면 강제로 list 페이지로
        if(auth==null) {
            return "redirect:/list";
        }

        model.addAttribute("username", auth.getName());

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
                return "redirect:/list/page/1";
            }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){

        Optional<Item> result= itemRepository.findById(id);

        if(result.isPresent()) {
            model.addAttribute("item", result.get());
        } else {
            return "redirect:/list/page/1";
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

        return "redirect:/list/page/1";
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

    @GetMapping("/test2")
    String test2(){

        var result = new BCryptPasswordEncoder().encode("문자");

        System.out.println(result);
        return "redirect:/list";
    }


    @GetMapping("/list/page/{page}")
    String getListPage(@PathVariable int page, Model model) {

        // n번째 페이지, 페이지방 몇개?
        Page<Item> result =  itemRepository.findPageBy(PageRequest.of(page-1, 5));
        int pageNum = result.getTotalPages();

        model.addAttribute("items", result);
        model.addAttribute("page", pageNum);

        return "list.html";
    }







}
