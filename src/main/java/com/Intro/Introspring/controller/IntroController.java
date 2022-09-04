package com.Intro.Introspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroController {

    @GetMapping("intro")
    public String intro(Model model){
        model.addAttribute("data","hello!!");
        return "intro";
    }

    @GetMapping("intro-mvc")
    public String introMVC(@RequestParam(value = "name",required = false) String name,Model model){
        model.addAttribute("name",name);
        return "intro-MVC";
    }

    @GetMapping("intro-api")
    @ResponseBody
    public String introAPI(@RequestParam(value = "name") String name){
        return "intro"+name;
    }

    @GetMapping("intro-api2")
    @ResponseBody
    public Intro introAPI2(@RequestParam(value = "name") String name){
        Intro intro = new Intro();
        intro.setName(name);
        return intro;
    }

    static class Intro{
        private String name;

        public String getName(){
            return  name;
        }

        public void setName(String name){
            this.name = name;
        }

    }
}
