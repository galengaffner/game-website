package com.gngg.gamewebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "")
    public String homePage(Model model) {
        model.addAttribute("title", "Home Page");
        return "home-page";
    }
}
