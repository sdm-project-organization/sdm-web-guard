package com.mo.guard.repository;

import com.mo.guard.model.table.Auth;
import com.mo.guard.repository.core.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String>, CommonRepository<Auth> {

}
