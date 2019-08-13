package com.mo.guard.repository;

import com.mo.guard.model.table.User;
import com.mo.guard.repository.core.DummyRepository;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, DummyRepository<User> {

    /*@Query(value = "SELECT u.sequence, u.username, u.password, u.activeFlag, u.enableFlag, u.writer, u.editor, u.createdDate, u.updatedDate " +
            "FROM User u " +
            "WHERE u.username = :username and u.enableFlag = :enableFlag")*/
    User findByUsernameAndEnableFlag(String username, byte enableFlag);




}
