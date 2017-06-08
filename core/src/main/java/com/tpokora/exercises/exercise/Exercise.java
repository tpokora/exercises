package com.tpokora.exercises.exercise;

import com.tpokora.exercises.common.AbstractEntity;

/**
 * Created by pokor on 08.06.2017.
 */
public class Exercise extends AbstractEntity {

    private String name;
    private String description;

    public Exercise() {

    }

    public Exercise(Integer id) {
        super(id);
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
