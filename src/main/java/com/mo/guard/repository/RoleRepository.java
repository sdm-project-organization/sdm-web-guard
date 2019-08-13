package com.mo.guard.repository;

import com.mo.guard.model.table.Role;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, DummyRepository<Role> {

    public int countBySequenceIn(List<Integer> sequences);

    public List<Role> findAllBySequenceIn(List<Integer> sequences);

}
