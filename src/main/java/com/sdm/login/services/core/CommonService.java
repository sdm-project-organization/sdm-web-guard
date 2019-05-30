package com.sdm.login.services.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonService<T> {

    // count

    default long count() {
        return 0;
    };

    // find

    default T findBySequence(int sequence) {
        return null;
    };

    default T findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return null;
    };

    default T findBySequenceAndActiveFlagAndEnableFlag(int sequence, byte activeFlag, byte enableFlag) {
        return null;
    };

    default List<T> findAllByDisplayName(String displayName) {
        return null;
    };

    default List<T> findAllByDisplayNameAndEnableFlag(String displayName, byte enableFlag) {
        return null;
    };

    default List<T> findAllByDisplayNameAndActiveFlagAndEnableFlag(String displayName, byte activeFlag, byte enableFlag) {
        return null;
    };

    default Page<T> findAllByPage(int offset, int limit) {
        return null;
    };

    // save

    default T save(T obj) throws Exception {
        return null;
    }

    default List<T> saveAll(List<T> list) {
        return null;
    }

    // update

    default void updateBySequence(int sequence, T tree) throws Exception {

    }

    default void active(int sequence) {

    }

    default void unactive(int sequence) {

    }

    default void enable(int sequence) {

    }

    default void unenable(int sequence) throws Exception {

    }

}
