package com.mo.guard.repository;

import com.mo.guard.model.entity.ResourceEntity;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, String>, DummyRepository<ResourceEntity> {

}
