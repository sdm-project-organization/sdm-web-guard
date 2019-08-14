package com.mo.guard.repository.relation;

import com.mo.guard.model.entity.relation.RelationUserRole;
import com.mo.guard.repository.core.RelationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationUserRoleRepository extends JpaRepository<RelationUserRole, String>, RelationRepository<RelationUserRole> {

    public List<RelationUserRole> findByUserSequenceAndEnableFlag(
            int userSequence,
            byte enableFlag);

    /*public List<RelationAuthResource> findByAuthSequenceAndResourceSequenceInAndEnableFlag(
            int authSequence,
            List<Integer> resourceSequences,
            byte enableFlag);*/

}
