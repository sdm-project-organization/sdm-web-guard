package com.mo.guard.repository;

import com.mo.guard.model.table.App;
import com.mo.guard.repository.core.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App, String>, CommonRepository<App> {


}
