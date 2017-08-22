package com.tpokora.exercises.workout.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "Day of workout")
@Entity
@Table(name = "DAY")
public class Day extends AbstractEntity {

    @ApiModelProperty(name = "Workout day")
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty(name = "Workout reference")
    @JoinColumn(name = "WORKOUT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Workout workout;

    @ApiModelProperty(name = "Sets of exercises")
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ExerciseSet> exerciseSets;

    public Day() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
