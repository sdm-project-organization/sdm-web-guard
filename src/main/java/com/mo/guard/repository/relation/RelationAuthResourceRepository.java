package com.mo.guard.repository.relation;

import com.mo.guard.model.table.relation.RelationAuthResource;
import com.mo.guard.repository.core.RelationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationAuthResourceRepository extends JpaRepository<RelationAuthResource, String>, RelationRepository<RelationAuthResource> {

    public List<RelationAuthResource> findByAuthSequenceAndEnableFlag(
            int authSequence,
            byte enableFlag);

    /*public List<RelationAuthResource> findByAuthSequenceAndResourceSequenceInAndEnableFlag(
            int authSequence,
            List<Integer> resourceSequences,
            byte enableFlag);*/

}
