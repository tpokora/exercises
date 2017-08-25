package com.tpokora.exercises.workout.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpokora.exercises.common.AbstractEntity;
import com.tpokora.exercises.exercise.model.Exercise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value = "Exercise set", description = "Exercise set for workout model")
@Entity
@Table(name = "EXERCISESET")
public class ExerciseSet extends AbstractEntity {

    @ApiModelProperty(name = "Exercise reference", required = true)
    @JoinColumn(name = "EXERCISE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_exerciseid"))
    @OneToOne
    private Exercise exercise;

    @ApiModelProperty(name = "Number of sets", required = true)
    @Column(name = "SETS")
    private Integer sets;

    @ApiModelProperty(name = "Number of reps per set", required = true)
    @Column(name = "REPS")
    private Integer reps;

    @ApiModelProperty(name = "Workout day reference", required = true)
    @JoinColumn(name = "DAY_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_dayid"), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference(value = "day-in-exercise-set")
    private Day day;

    public ExerciseSet() {}

    public ExerciseSet(Exercise exercise, Integer sets, Integer reps, Day day) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.day = day;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
