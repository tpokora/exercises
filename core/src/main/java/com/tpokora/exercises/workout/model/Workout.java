package com.tpokora.exercises.workout.model;

import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel(value = "Workout", description = "Workout Model")
@Entity
@Table(
        name = "WORKOUT",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "NAME" },
                name = "uk_name"
        ))
public class Workout extends AbstractEntity{

    @ApiModelProperty(name = "Workout name", required = true)
    @Column(name = "NAME", unique = true)
    private String name;

    @ApiModelProperty(name = "Workout description")
    @Column(name = "DESCRIPTION")
    @Lob
    @Size(max = 9000)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ApiModelProperty(name = "Days of workout")
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<Day> days;

    public Workout() {}

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
