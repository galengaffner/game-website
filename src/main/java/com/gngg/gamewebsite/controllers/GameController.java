package com.gngg.gamewebsite.controllers;

import com.gngg.gamewebsite.models.FirstGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("game")
public class GameController {
    private FirstGame game;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String gameHomeGet(Model model){
        model.addAttribute("title", "GrapSheebMacWhaley");
        model.addAttribute(new FirstGame());
        return "game/game-home";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String gameHomePost(@ModelAttribute @Valid FirstGame game, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "GrapSheebMacWhaley");
            model.addAttribute(game);
            return "game/game-home";
        }
        this.game = game;
        return "redirect:/game/season-page";
    }

    @RequestMapping(value = "season-page", method = RequestMethod.GET)
    public String regularSeasonGet(Model model){
        model.addAttribute("title", "Regular Season");
        return "game/season-page";
    }

    @RequestMapping(value = "season-page", method = RequestMethod.POST)
    public String regularSeasonPost(){
        return "game/season-page";
    }
}
