package com.mo.guard.repository.core;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RelationRepository<T extends Object> extends BaseRepository<T> {

    // pk

    /*T findByKey(M key);*/

    /*T findByKeyAndEnableFlag(M key, byte enableFlag);*/

}
