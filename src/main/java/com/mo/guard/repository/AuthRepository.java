package com.mo.guard.repository;

import com.mo.guard.model.entity.AuthEntity;
import com.mo.guard.repository.core.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, String>, DummyRepository<AuthEntity> {

}
