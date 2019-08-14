package com.mo.guard.model.resource;

import com.mo.guard.model.entity.AppEntity;

public class AppResource extends CommonResource<AppEntity, AppResource>{


    @Override
    public AppResource toInsert() throws Exception {
        return null;
    }

    @Override
    public AppResource toUpdate() throws Exception {
        return null;
    }

    @Override
    public AppEntity toEntity() {
        return null;
    }
}
