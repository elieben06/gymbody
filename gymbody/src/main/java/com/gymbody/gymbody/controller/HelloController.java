package com.gymbody.gymbody.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//http://localhost:8080
@RestController
public class HelloController {

    //http://localhost:8080/api/hello
    @GetMapping("/api/hello")
    public String hello(){
        return "Hello from GymBody!";
    }
    //http://localhost:8080/api/welcome
    @GetMapping("/api/welcome")
    public String welcome(){
        return "Welcome to GymBody! Let's build your body.";
    }


}
