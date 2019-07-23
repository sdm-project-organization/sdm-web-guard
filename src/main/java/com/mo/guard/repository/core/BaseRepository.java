package com.mo.guard.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends Object> extends CrudRepository<T, String> {

    // common

    List<T> findAllByEnableFlag(byte enableFlag);

    /*Page<T> findAllByEnableFlag(byte enableFlag, Pageable pageable);*/

    Page<T> findAllByActiveFlagAndEnableFlag(byte activeFlag, byte enableFlag, Pageable pageable);

    // displayName

    List<T> findAllByDisplayName(String displayName);

    List<T> findAllByDisplayNameAndEnableFlag(String displayName, byte enableFlag);

    List<T> findByDisplayNameAndActiveFlagAndEnableFlag(String displayName, byte activeFlag, byte enableFlag);

}
