package com.tpokora.exercises.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pokor on 08.06.2017.
 */
public abstract class AbstractEntity {

    @ApiModelProperty(value = "Item ID", required = true)
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
