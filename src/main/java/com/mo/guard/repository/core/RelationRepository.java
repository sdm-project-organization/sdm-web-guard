package com.mo.guard.repository.core;

import com.mo.guard.model.table.relation.RelationAuthResource;

public interface RelationRepository<T> extends BaseRepository<T> {

    RelationAuthResource findByPk(String pk);

    RelationAuthResource findByPkAndEnableFlag(String pk, byte enableFlag);
}
