package org.example.demo.controllers;

import jdk.jfr.Registered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerView {
    @GetMapping
    public String index() {
        return "index";
    }
    @RequestMapping(method = RequestMethod.GET,path = "/hello")
    public ModelAndView he(ModelAndView modelAndView){
        modelAndView.setViewName("hello");
        return modelAndView;
    }

}
