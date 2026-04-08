package com.gymbody.gymbody.controller;

import com.gymbody.gymbody.dto.WorkoutRequestDto;
import com.gymbody.gymbody.service.ClaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final ClaudeService claudeService;

    @PostMapping("/generate")
    public String generate (@RequestBody WorkoutRequestDto request){
        return claudeService.generateWorkout(request);
    }
}
