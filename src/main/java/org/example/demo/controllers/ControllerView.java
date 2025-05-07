package org.example.demo.controllers;

import org.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sdf")
public class ControllerView {


    private final UserService userService;

    @Autowired
    public ControllerView(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {

        return "mainPage";
    }

    @GetMapping("/test/{id}")
    @ResponseBody
    public String test(@PathVariable("id") String id) {
        System.out.println(id);
        return "home";
    }







}
