package com.mo.guard.repository;

import com.mo.guard.model.table.User;
import com.mo.guard.repository.core.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, CommonRepository<User> {

}
