package com.mo.guard.repository.relation;

import com.mo.guard.model.entity.relation.RelationRoleAuth;
import com.mo.guard.repository.core.RelationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRoleAuthRepository extends JpaRepository<RelationRoleAuth, String>, RelationRepository<RelationRoleAuth> {

    public List<RelationRoleAuth> findByRoleSequenceAndEnableFlag(
            int authSequence,
            byte enableFlag);

    /*public List<RelationAuthResource> findByAuthSequenceAndResourceSequenceInAndEnableFlag(
            int authSequence,
            List<Integer> resourceSequences,
            byte enableFlag);*/

}
