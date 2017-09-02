package com.tpokora.exercises.workout.domain;

import com.tpokora.exercises.workout.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {

    List<Day> findByWorkout_Id(Integer id);

}
