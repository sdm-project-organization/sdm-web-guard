package com.mo.guard.repository;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.UserEntity;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, DummyRepository<UserEntity> {

    /*@Query(value = "SELECT u.sequence, u.username, u.password, u.activeFlag, u.enableFlag, u.writer, u.editor, u.createdDate, u.updatedDate " +
            "FROM UserEntity u " +
            "WHERE u.username = :username and u.enableFlag = :enableFlag")*/
    UserEntity findByUsernameAndEnableFlag(String username,
                                           EnableFlag enableFlag);




}
