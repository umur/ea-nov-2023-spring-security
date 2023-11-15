package edu.miu.jwtsecurity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PostMapping("/processRequest")
    public String processRequest(@RequestBody String request) {
        // Assuming the request contains a username as a string
        return "Request processed successfully!";
    }
}
