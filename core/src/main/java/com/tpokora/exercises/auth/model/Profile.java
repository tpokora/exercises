package com.tpokora.exercises.auth.model;

import com.tpokora.exercises.common.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value = "User profile")
@Entity
@Table(
        name = "PROFILE",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "EMAIL" },
                        name = "uk_index_email"
                )
        }
)
public class Profile extends AbstractEntity{

    @ApiModelProperty(name = "User name")
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty(name = "User profile google token")
    @Column(name = "TOKEN")
    private byte[] token;

    @ApiModelProperty(name = "User email")
    @Column(name = "EMAIL")
    private String email;

    public Profile() {}

    public Profile(String name, byte[] token, String email) {
        this.name = name;
        this.token = token;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
