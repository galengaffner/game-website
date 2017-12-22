package com.gngg.gamewebsite.controllers;

import com.gngg.gamewebsite.models.FirstGame;
import com.gngg.gamewebsite.models.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute(game);
        model.addAttribute("showCategory", false);
        return "game/season-page";
    }

    @RequestMapping(value = "season-page", method = RequestMethod.POST)
    public String regularSeasonPost(Model model, @RequestParam("btn") String btnID,
                                    @RequestParam("win") boolean win){

        if(btnID.equals("category")){
            model.addAttribute("showCategory", true);
            model.addAttribute("category", game.getRandomCategory());
        }
        else{
            if(game.gameResult(btnID, win)){
                return "redirect:/game/break-page";
            }
        }
        model.addAttribute("title", "Regular Season");
        model.addAttribute(game);
        return "game/season-page";
    }

    @RequestMapping(value = "break-page", method = RequestMethod.GET)
    public String breakGet(Model model){
        model.addAttribute("title", "Break Time");
        return "game/break-page";
    }

    @RequestMapping(value = "playoff-page", method = RequestMethod.GET)
    public String playoffGet(Model model){
        model.addAttribute("title", "Playoffs");
        model.addAttribute(game);
        return "game/playoff-page";
    }

    @RequestMapping(value = "playoff-page", method = RequestMethod.POST)
    public String playoffPost(Model model, @RequestParam("btn") String btnID,
                              @RequestParam("win") boolean win){
        if(game.updatePlayoffMatchup(btnID, win)){
            return "redirect:/game/winner-page";
        }
        return "game/playoff-page";
    }

    @RequestMapping(value = "winner-page", method = RequestMethod.GET)
    public String winnerPageGet(Model model){
        model.addAttribute("title", "Champion!");
        model.addAttribute(game);
        return "game/winner-page";
    }
}
