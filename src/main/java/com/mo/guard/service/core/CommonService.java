package com.mo.guard.service.core;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonService<T> {

    // count

    default long count() {
        return 0;
    }

    // find

    default T findByPk(String pk) {
        return null;
    }

    default T findByPkAndEnableFlag(String pk, byte enableFlag) {
        return null;
    }

    default T findBySequence(int sequence) {
        return null;
    }

    default T findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return null;
    }

    default T findBySequenceAndActiveFlagAndEnableFlag(int sequence, byte activeFlag, byte enableFlag) {
        return null;
    }

    default T findByDisplayNameAndEnableFlag(String displayName, byte enableFlag) {
        return null;
    }

    default List<T> findAllByEnableFlag(byte enableFlag) {
        return null;
    }

    default List<T> findAllByDisplayName(String displayName) {
        return null;
    }

    default List<T> findAllByDisplayNameAndEnableFlag(String displayName, byte enableFlag) {
        return null;
    }

    default List<T> findAllByDisplayNameAndActiveFlagAndEnableFlag(String displayName, byte activeFlag, byte enableFlag) {
        return null;
    }

    default Page<T> findAllByPage(int offset, int limit) {
        return null;
    }

    // save

    default T save(T obj) throws RuntimeException { return null; }

    default List<T> saveAll(List<T> list) throws RuntimeException {
        return null;
    }

    // update

    /*default void updateBySequence(int sequence, T tree) throws RuntimeException { }*/

    default T updateBySequence(int sequence, T tree) throws RuntimeException { return null; }

    default T active(int sequence) {
        return null;
    }

    default T unactive(int sequence) {
        return null;
    }

    default T enable(int sequence) {
        return null;
    }

    default T unenable(int sequence) throws RuntimeException {
        return null;
    }

}