package com.mo.guard.repository.core;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, String> {

    // common - list

    List<T> findAllByEnableFlag(EnableFlag enableFlag);

    // common - page

    /*Page<T> findAllByEnableFlag(byte enableFlag, Pageable pageable);*/

    Page<T> findAllByActiveFlagAndEnableFlag(ActiveFlag activeFlag,
                                             EnableFlag enableFlag,
                                             Pageable pageable);

    // displayName

    List<T> findAllByDisplayName(String displayName);

    List<T> findAllByDisplayNameAndEnableFlag(String displayName,
                                              EnableFlag enableFlag);

    List<T> findByDisplayNameAndActiveFlagAndEnableFlag(String displayName,
                                                        ActiveFlag activeFlag,
                                                        EnableFlag enableFlag);

}
