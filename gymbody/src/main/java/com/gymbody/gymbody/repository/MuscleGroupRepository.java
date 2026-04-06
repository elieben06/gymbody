package com.gymbody.gymbody.repository;

import com.gymbody.gymbody.model.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleGroupRepository
        extends JpaRepository<MuscleGroup, Long> {

}
