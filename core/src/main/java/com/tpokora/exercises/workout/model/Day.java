package com.tpokora.exercises.workout.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "Day of workout")
@Entity
@Table(
        name = "DAY",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = { "INDEX", "WORKOUT_ID" },
                    name = "uk_index_workoutid")
        }
)
public class Day extends AbstractEntity {

    @ApiModelProperty(name = "Workout day name")
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty(name = "Day index")
    @Column(name = "INDEX")
    private Integer index;

    @ApiModelProperty(name = "Workout reference")
    @JoinColumn(name = "WORKOUT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Workout workout;

    @ApiModelProperty(name = "Sets of exercises")
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<ExerciseSet> exerciseSets;

    public Day() {}

    public Day(String name, Integer index, Workout workout, List<ExerciseSet> exerciseSets) {
        this.name = name;
        this.index = index;
        this.workout = workout;
        this.exerciseSets = exerciseSets;
    }

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
