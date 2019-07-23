package com.mo.guard.repository.core;

public interface DummyRepository<T> extends BaseRepository<T> {

    // sequence

    T findBySequence(int sequence);

    T findBySequenceAndEnableFlag(int sequence, byte enableFlag);

    T findBySequenceAndActiveFlagAndEnableFlag(int sequence, byte activeFlag, byte enableFlag);
}
