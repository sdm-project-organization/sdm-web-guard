package com.mo.guard.repository.core;

import com.mo.guard.model.table.relation.RelationAuthResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface RelationRepository<T extends Object> extends BaseRepository<T> {

    // pk

    RelationAuthResource findByPk(String pk);

    RelationAuthResource findByPkAndEnableFlag(String pk, byte enableFlag);

}
