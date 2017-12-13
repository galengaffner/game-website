package com.gngg.gamewebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("game")
public class GameController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String gameHome(Model model){
        model.addAttribute("title", "GrapSheebMacWhaley");
        return "game/game-home";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String gameHomePost(@RequestParam Integer numberTeams,
                               @RequestParam Integer seasonGames,
                               @RequestParam Integer playoffGames){
        // TODO: build the game model
        return "redirect:/game"; // TODO: redirect to the game page
    }
}
