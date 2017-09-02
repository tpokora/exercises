package com.tpokora.exercises.exercise.domain;

import com.tpokora.exercises.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pokor on 15.06.2017.
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    List<Exercise> findByNameContainingIgnoreCase(String name);
}
