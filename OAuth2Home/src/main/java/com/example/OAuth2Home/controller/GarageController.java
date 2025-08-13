package com.example.OAuth2Home.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garage")
public class GarageController {
    @RequestMapping(path = "/entry", method = RequestMethod.GET)
    public String getEntry() {
        return "Authorized and Non Authorized entry into Garage";
    }
}
