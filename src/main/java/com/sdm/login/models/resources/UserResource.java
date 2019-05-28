package com.sdm.login.models.resources;

import com.sdm.login.models.tables.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserResource extends CommonResource<User, UserResource> implements Serializable {

    private Integer serviceSequence;

    @NotNull
    private String userId;

    @NotNull
    private String password;

    private String email;
    private String name;
    private String meta;

    @Override
    public UserResource toInsert() throws Exception {
        return null;
    }

    @Override
    public UserResource toUpdate() throws Exception {
        return null;
    }

    @Override
    public User toEntity() {
        return null;
    }
}
