package com.tpokora.exercises.common;

/**
 * Created by pokor on 08.06.2017.
 */
public abstract class AbstractEntity {

    private Integer id;

    public AbstractEntity() {}

    public AbstractEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
