package com.mo.guard.repository.core;

import com.mo.guard.model.table.relation.RelationAuthResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface RelationRepository<T extends Object, M> extends BaseRepository<T> {

    // pk

    T findByKey(M key);

    T findByKeyAndEnableFlag(M key, byte enableFlag);

}
