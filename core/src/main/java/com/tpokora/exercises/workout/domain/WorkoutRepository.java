package com.tpokora.exercises.workout.domain;

import com.tpokora.exercises.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

}
