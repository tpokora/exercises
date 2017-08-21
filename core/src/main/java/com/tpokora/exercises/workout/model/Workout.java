package com.tpokora.exercises.workout.model;

import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
    @Size(min = 0, max = 9000)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

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
}
