package com.gymbody.gymbody.controller;

import com.gymbody.gymbody.model.MuscleGroup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    //http://localhost:8080/api/muscles/example
    @GetMapping("/api/muscles/example")
    public MuscleGroup muscles(){
        MuscleGroup m = new MuscleGroup();
        m.setName("Biceps");
        m.setDescription("Upper arm muscle");
        m.setBodyZone("upper");
        return m;
    }
    //http://localhost:8080/api/muscles/all
    @GetMapping("/api/muscles/all")
    public List<MuscleGroup> all(){
        List<MuscleGroup> list = new ArrayList<>();

        MuscleGroup m1 = new MuscleGroup();
        MuscleGroup m2 = new MuscleGroup();
        MuscleGroup m3 = new MuscleGroup();

        m1.setName("Quadriceps");
        m1.setDescription("Front thigh muscles responsible for knee extension.");
        m1.setBodyZone("Legs");

        m2.setName("Gluteus Maximus");
        m2.setDescription("The largest glute muscle responsible for hip extension and power.");
        m2.setBodyZone("Glutes");

        m3.setName("Latissimus Dorsi");
        m3.setDescription("Large back muscle used in pulling movements.");
        m3.setBodyZone("Back");

        list.add(m1);
        list.add(m2);
        list.add(m3);

        return list;
    }


}
