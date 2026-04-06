package com.gymbody.gymbody.controller;

import com.gymbody.gymbody.model.MuscleGroup;
import com.gymbody.gymbody.service.MuscleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/muscles")
@RequiredArgsConstructor
public class MuscleGroupController {

    private final MuscleGroupService service;

    @GetMapping

    public List<MuscleGroup> getAll(){
        return service.getAll();
    }
}
