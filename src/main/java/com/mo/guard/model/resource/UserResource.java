package com.mo.guard.model.resource;

import com.mo.guard.model.table.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserResource extends CommonResource<User, UserResource> implements Serializable {

    private Integer serviceSequence;

    @NotNull
    private String username;

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
