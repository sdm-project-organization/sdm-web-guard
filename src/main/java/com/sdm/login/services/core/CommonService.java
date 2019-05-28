package com.sdm.login.services.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonService<T> {

    // count

    long count();

    // find

    T findBySequence(int sequence);

    T findBySequenceAndEnableFlag(int sequence, byte enableFlag);

    T findBySequenceAndActiveFlagAndEnableFlag(int sequence, byte activeFlag, byte enableFlag);

    List<T> findAllByDisplayName(String displayName);

    List<T> findAllByDisplayNameAndEnableFlag(String displayName, byte enableFlag);

    List<T> findAllByDisplayNameAndActiveFlagAndEnableFlag(String displayName, byte activeFlag, byte enableFlag);

    Page<T> findAllByPage(int offset, int limit);

    // save

    T save(T obj) throws Exception;

    List<T> saveAll(List<T> list);

    // update

    void updateBySequence(int sequence, T tree) throws Exception;

    void active(int sequence);

    void unactive(int sequence);

    void enable(int sequence);

    void unenable(int sequence) throws Exception;

}
