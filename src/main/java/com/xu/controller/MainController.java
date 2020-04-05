package com.xu.controller;

import com.xu.entity.Item;
import com.xu.entity.ShopInfo;
import com.xu.service.ItemService;
import com.xu.service.ShopInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MainController {

    @Resource
    private ItemService itemService;

    @Resource
    private ShopInfoService shopInfoService;

    @GetMapping(value = "/**/{path:[^\\.]*}")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();
        List<Item> items = itemService.findAll();
        mav.addObject("title", "Shop");
        mav.addObject("items", items);
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/home")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView();
        List<Item> items = itemService.findAll();
        ShopInfo shopInfo = getShopInfo();
        mav.addObject("title", "Shop");
        mav.addObject("items", items);
        mav.addObject("shopName", shopInfo.getName());
        mav.addObject("shopContent", shopInfo.getContent());
        mav.addObject("shopContact", shopInfo.getContact());
        mav.setViewName("main");
        return mav;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String toAdmin(){
        return "admin/adminPage";
    }

    private ShopInfo getShopInfo(){
        List<ShopInfo> shopInfos = shopInfoService.findAll();
        if(shopInfos.isEmpty()){
            ShopInfo shopInfo = new ShopInfo();
            shopInfo.setName("爱艺购");
            shopInfo.setContent("经营各种食品百货");
            shopInfo.setContact("");
            shopInfoService.save(shopInfo);
            return shopInfo;
        }
        return shopInfos.get(0);
    }
}
