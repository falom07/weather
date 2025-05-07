package org.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/searchNewWeather")
public class NewWeatherController {

    @GetMapping("/")
    public String searchNewWeather(Model model) {
        return "searchNewWeather";
    }
}
