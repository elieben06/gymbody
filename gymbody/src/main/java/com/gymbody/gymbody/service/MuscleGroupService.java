package com.gymbody.gymbody.service;

import com.gymbody.gymbody.model.MuscleGroup;
import com.gymbody.gymbody.repository.MuscleGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuscleGroupService {

    private final MuscleGroupRepository repository;

    public List<MuscleGroup> getAll(){
        return repository.findAll();
    }

}
