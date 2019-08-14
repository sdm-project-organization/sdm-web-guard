package com.mo.guard.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DummyRepository<T extends Object> extends BaseRepository<T> {

    // sequence

    T findBySequence(int sequence);

    T findBySequenceAndEnableFlag(int sequence, byte enableFlag);

    T findBySequenceAndActiveFlagAndEnableFlag(int sequence, byte activeFlag, byte enableFlag);

    T findByDisplayNameAndEnableFlag(String displayName, byte enableFlag);


}
