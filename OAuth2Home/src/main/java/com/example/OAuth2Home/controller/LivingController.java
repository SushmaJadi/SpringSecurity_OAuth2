package com.example.OAuth2Home.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/living_room")
public class LivingController {

    @RequestMapping(path = "/entry", method = RequestMethod.GET)
    public String getEntry() {
        return "Authorized and Non Authorized Entry into Living Area";
    }
}
