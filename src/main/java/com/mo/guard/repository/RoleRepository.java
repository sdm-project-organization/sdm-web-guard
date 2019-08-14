package com.mo.guard.repository;

import com.mo.guard.model.entity.RoleEntity;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String>, DummyRepository<RoleEntity> {

    public int countBySequenceIn(List<Integer> sequences);

    public List<RoleEntity> findAllBySequenceIn(List<Integer> sequences);

}
