package com.mo.guard.service.relation;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.relation.RelationUserRole;
import com.mo.guard.repository.relation.RelationUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationUserRoleServiceImpl implements RelationUserRoleService {

    @Autowired
    RelationUserRoleRepository relationUserRoleRepository;

    @Override
    public List<RelationUserRole> findAllByEnableFlag(byte enableFlag) {
        return relationUserRoleRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public RelationUserRole save(RelationUserRole obj) {
        return relationUserRoleRepository.save(obj);
    }

    @Override
    public RelationUserRole findByPkAndEnableFlag(String id, byte enableFlag) {
        /*RelationUserRoleId relationAuthResourceId = new RelationUserRoleId(
                parseInt(id.split("-")[0]),
                parseInt(id.split("-")[1]));
        return relationUserRoleRepository.findByKeyAndEnableFlag(relationAuthResourceId, enableFlag);*/
        return null;
    }

    @Override
    public RelationUserRole updateBySequence(int sequence, RelationUserRole targetRelationUserRole) {
        RelationUserRole originRelationUserRole = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originRelationUserRole.setDisplayName(targetRelationUserRole.getDisplayName());
        originRelationUserRole.setDisplayOrder(targetRelationUserRole.getDisplayOrder());
        originRelationUserRole.setDesc(targetRelationUserRole.getDesc());
        relationUserRoleRepository.flush();
        return originRelationUserRole;
    }

    @Override
    public RelationUserRole unenable(int sequence) {
        RelationUserRole relationAuthResource = findBySequence(sequence);
        relationAuthResource.setEnableFlag(EnableFlag.N.getValue());
        relationUserRoleRepository.flush();
        return relationAuthResource;
    }
}
