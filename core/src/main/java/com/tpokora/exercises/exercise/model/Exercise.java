package com.tpokora.exercises.exercise.model;

import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pokor on 08.06.2017.
 */
@ApiModel(value = "Exercise", description = "Exercise model")
public class Exercise extends AbstractEntity {

    @ApiModelProperty(value = "Exercise name", required = true)
    private String name;

    @ApiModelProperty(value = "Exercise description")
    private String description;

    public Exercise() {

    }

    public Exercise(Integer id) {
        super(id);
    }

    public Exercise(Integer id, String name, String description) {
        this(id);
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
}
