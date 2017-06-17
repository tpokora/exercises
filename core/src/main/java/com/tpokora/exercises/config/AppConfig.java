package com.tpokora.exercises.config;

import com.tpokora.exercises.exercise.service.ExerciseService;
import com.tpokora.exercises.exercise.service.ExerciseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pokor on 08.06.2017.
 */
@Configuration
public class AppConfig {

    @Bean(name = "exerciseService")
    public ExerciseService getExerciseService() {
        return new ExerciseServiceImpl();
    }
}