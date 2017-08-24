package com.tpokora.exercises.workout.domain;

import com.tpokora.exercises.workout.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Integer> {
}
