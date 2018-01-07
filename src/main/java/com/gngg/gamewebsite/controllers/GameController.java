package com.gngg.gamewebsite.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gngg.gamewebsite.models.FirstGame;
import com.gngg.gamewebsite.models.Team;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("game")
public class GameController {
    private FirstGame game;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String gameHomeGet(Model model){
        model.addAttribute("title", "GameSeven");
        game = new FirstGame();
        model.addAttribute(game);
        return "game/game-home";
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String gameHomePost(@ModelAttribute @Valid FirstGame game, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "GamesSeven");
            model.addAttribute(game);
            return "game/game-home";
        }
        this.game = game;
        return "redirect:/game/season-page";
    }

    @RequestMapping(value = "team-names", method = RequestMethod.GET)
    public String teamNamesGet(Model model){
        model.addAttribute("title", "Set Team Names");
        model.addAttribute(game);
        return "game/team-names";
    }


    @RequestMapping(value = "team-names", method = RequestMethod.POST)
    public String teamNamesPost(@RequestParam("teamList") List<Team> teams){
        game.setTeams(teams);
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
        model.addAttribute("showCategory", false);
        return "game/playoff-page";
    }

    @RequestMapping(value = "playoff-page", method = RequestMethod.POST)
    public String playoffPost(Model model, @RequestParam("btn") String btnID,
                              @RequestParam("win") boolean win){
        if(btnID.equals("category")){
            model.addAttribute("showCategory", true);
            model.addAttribute("category", game.getRandomCategory());
        }
        else if(game.updatePlayoffMatchup(btnID, win)){
            return "redirect:/game/winner-page";
        }
        model.addAttribute("title", "Playoffs");
        model.addAttribute(game);
        return "game/playoff-page";
    }

    @RequestMapping(value = "winner-page", method = RequestMethod.GET)
    public String winnerPageGet(Model model){
        model.addAttribute("title", "Champion!");
        model.addAttribute(game);
        return "game/winner-page";
    }
}
