package com.example.OAuth2Home.controller;

import com.example.OAuth2Home.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/kitchen")
@RestController
public class KitchenController {
    @Autowired
   // @Qualifier("kitchenService")
    private UserService userService;

    Logger  logger = LoggerFactory.getLogger(KitchenController.class);


    @RequestMapping(method = RequestMethod.GET,path = "/entry")
    public String getEntry() {
        return "Authorised Entry into Kitchen";
    }
    @RequestMapping(method = RequestMethod.GET,path = "/entry/user/{userName}")
    public ResponseEntity<String> getUserEntry(@PathVariable(name = "userName") String userName) {
        logger.info("kitchen user:"+ userName);
        userService.loadUserByUsername(userName);
        return new ResponseEntity<>("Authorised:"+ userName+ "entry into kitchen", null, 200);
    }
    @RequestMapping(path = "/entry/auth/{id}", method = RequestMethod.GET)
    public String getAuthEntry(@PathVariable(name = "id") String id) {
        System.out.println(id+"........");
        userService.getUserDetails(Integer.parseInt(id));
        return "Authorised Entry into Kitchen";
    }
}
