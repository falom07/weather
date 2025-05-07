package org.example.demo.controllers;


import org.example.demo.DTO.FullLocationDTO;
import org.example.demo.Service.LocationService;
import org.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {
    private final LocationService locationService;
    private final UserService userService;

    public HomePageController(LocationService locationService, UserService userService) {
        this.locationService = locationService;
        this.userService = userService;
    }


    @GetMapping
    public String openMainPage(@SessionAttribute("loginID")String loginID , Model model) throws URISyntaxException, IOException, InterruptedException {
        int loginId = Integer.parseInt(loginID);


        String login = userService.getLoginByID(loginID);
        List<FullLocationDTO> listOfLocations = locationService.getAllWeatherByUserID(loginId);

        model.addAttribute("locations", listOfLocations);
        model.addAttribute("login", login);


        return "mainPage";
    }
    @PostMapping("/location/deleteCard")
    public String deleteCard(@ModelAttribute("location") FullLocationDTO location,@SessionAttribute("loginID") String loginID) throws URISyntaxException, IOException, InterruptedException {
        BigDecimal longitude = BigDecimal.valueOf(location.getCoordinate().getLon());
        BigDecimal latitude = BigDecimal.valueOf(location.getCoordinate().getLat());

        locationService.deleteLocation(longitude,latitude,loginID);

        return "redirect:/";
    }
}
