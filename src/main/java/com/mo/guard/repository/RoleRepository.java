package com.mo.guard.repository;

import com.mo.guard.model.table.Role;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, DummyRepository<Role> {

}
