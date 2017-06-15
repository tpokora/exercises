package com.tpokora.exercises.exercise.model;

import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Created by pokor on 08.06.2017.
 */
@ApiModel(value = "Exercise", description = "Exercise model")
@Entity
@Table(name = "EXERCISE")
public class Exercise extends AbstractEntity {

    @ApiModelProperty(value = "Exercise name", required = true)
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty(value = "Exercise description")
    @Column(name = "DESCRIPTION")
    @Lob
    @Size(min = 0, max = 9000)
    @Type(type = "org.hibernate.type.TextType")
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
