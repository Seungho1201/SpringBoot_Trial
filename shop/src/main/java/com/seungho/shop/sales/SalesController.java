package com.seungho.shop.sales;

import com.seungho.shop.Member.CustomUser;
import com.seungho.shop.Member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;

    @PostMapping("/sales")
    String sales(@RequestParam String itemName,
                 @RequestParam Integer price,
                 @RequestParam Integer count,
                 Authentication auth) {

        CustomUser user = (CustomUser) auth.getPrincipal();

        System.out.println(user.getUsername());
        System.out.println(itemName);
        System.out.println(price);
        System.out.println(count);


        var data = new Sales();

        var member = new Member();
        member.setId(1L);

        data.setItemName(itemName);
        data.setMember(member);
        data.setPrice(price);
        data.setCount(count);

        salesRepository.save(data);

        return "redirect:/list/page/1";
    }

    @GetMapping("/salelist")
    String salelist(Model model) {

        //model.addAttribute("salelist", salesRepository.findAll());

        List<Sales> sales = salesRepository.customFindAll();
        System.out.println(sales);

        //System.out.println(salesRepository.findAll());
        return "salelist.html";
    }
}
