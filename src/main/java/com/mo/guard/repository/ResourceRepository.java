package com.mo.guard.repository;

import com.mo.guard.model.table.Resource;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>, DummyRepository<Resource> {

}
