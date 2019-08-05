package com.mo.guard.repository;

import com.mo.guard.model.table.User;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, DummyRepository<User> {


    User findByUsernameAndEnableFlag(String username, byte enableFlag);

}
