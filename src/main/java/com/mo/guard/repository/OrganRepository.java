package com.mo.guard.repository;

import com.mo.guard.model.entity.OrganEntity;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganRepository extends JpaRepository<OrganEntity, String>, DummyRepository<OrganEntity> {

}
