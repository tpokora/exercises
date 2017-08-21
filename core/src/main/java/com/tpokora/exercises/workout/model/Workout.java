package com.tpokora.exercises.workout.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel(value = "Workout", description = "Workout Model")
@Entity
@Table(name = "WORKOUT")
public class Workout extends AbstractEntity{

    @ApiModelProperty(name = "Workout name", required = true)
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty(name = "Workout description")
    @Column(name = "DESCRIPTION")
    @Lob
    @Size(max = 9000)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ApiModelProperty(name = "Sets of exercises")
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ExerciseSet> exerciseSets;

    public Workout() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
