package org.example.demo.controllers;

import org.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerView {


    private final UserService userService;

    @Autowired
    public ControllerView(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Добро пожаловать!");
//        userService.getAllUsers().forEach(System.out::println);
        System.out.println(userService.getOne().getLogin());
        System.out.println("hi");
        return "index"; // index.html в /WEB-INF/templates/
    }

    @GetMapping("/registraion")
    public String registraion(Model model) {
        model.addAttribute("message", "<UNK> <UNK>!");
        return "Registration";
    }

    @PostMapping("/register")
    public String registraion2(Model model) {
        System.out.println("hi");
        return "Registration";
    }


//    @GetMapping
//    public String index() {
//        System.out.println("index\n\n\n");
//        return "index";
//    }
//
//    @RequestMapping(method = RequestMethod.GET,path = "/hello")
//    public ModelAndView he(ModelAndView modelAndView){
//        modelAndView.setViewName("index");
//        System.out.println("index\n\n\n");
//        return modelAndView;
//    }

}
