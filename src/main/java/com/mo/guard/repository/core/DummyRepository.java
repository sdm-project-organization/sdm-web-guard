package com.mo.guard.repository.core;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DummyRepository<T extends Object> extends BaseRepository<T> {

    // sequence

    T findBySequence(int sequence);

    T findBySequenceAndEnableFlag(int sequence,
                                  EnableFlag enableFlag);

    T findBySequenceAndActiveFlagAndEnableFlag(int sequence,
                                               ActiveFlag activeFlag,
                                               EnableFlag enableFlag);

    T findByDisplayNameAndEnableFlag(String displayName,
                                     EnableFlag enableFlag);


}
