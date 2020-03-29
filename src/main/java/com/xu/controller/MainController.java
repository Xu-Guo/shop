package com.xu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Shop");
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
}
