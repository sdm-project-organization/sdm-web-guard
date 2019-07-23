package com.mo.guard.repository;

import com.mo.guard.model.table.Resource;
import com.mo.guard.repository.core.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>, CommonRepository<Resource> {

}
