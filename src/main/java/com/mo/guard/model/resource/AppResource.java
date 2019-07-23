package com.mo.guard.model.resource;

import com.mo.guard.model.table.App;

public class AppResource extends CommonResource<App, AppResource>{


    @Override
    public AppResource toInsert() throws Exception {
        return null;
    }

    @Override
    public AppResource toUpdate() throws Exception {
        return null;
    }

    @Override
    public App toEntity() {
        return null;
    }
}
