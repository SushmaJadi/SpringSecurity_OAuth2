package com.example.OAuth2Home.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @RequestMapping(path = "/entry", method = RequestMethod.GET)
    public String getEntry() {
        return "Authorised Entry into rooms";
    }
}
