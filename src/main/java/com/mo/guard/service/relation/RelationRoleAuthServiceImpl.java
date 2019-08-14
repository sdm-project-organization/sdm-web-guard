package com.mo.guard.service.relation;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.relation.RelationRoleAuth;
import com.mo.guard.repository.relation.RelationRoleAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationRoleAuthServiceImpl implements RelationRoleAuthService {

    @Autowired
    RelationRoleAuthRepository relationRoleAuthRepository;

    @Override
    public List<RelationRoleAuth> findAllByEnableFlag(byte enableFlag) {
        return relationRoleAuthRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public RelationRoleAuth save(RelationRoleAuth obj) {
        return relationRoleAuthRepository.save(obj);
    }

    @Override
    public RelationRoleAuth findByPkAndEnableFlag(String id, byte enableFlag) {
        /*RelationRoleAuthId relationAuthResourceId = new RelationRoleAuthId(
                parseInt(id.split("-")[0]),
                parseInt(id.split("-")[1]));
        return relationRoleAuthRepository.findByKeyAndEnableFlag(relationAuthResourceId, enableFlag);*/
        return null;
    }

    @Override
    public RelationRoleAuth updateBySequence(int sequence, RelationRoleAuth targetRelationRoleAuth) {
        RelationRoleAuth originRelationRoleAuth = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originRelationRoleAuth.setDisplayName(targetRelationRoleAuth.getDisplayName());
        originRelationRoleAuth.setDisplayOrder(targetRelationRoleAuth.getDisplayOrder());
        originRelationRoleAuth.setDesc(targetRelationRoleAuth.getDesc());
        relationRoleAuthRepository.flush();
        return originRelationRoleAuth;
    }

    @Override
    public RelationRoleAuth unenable(int sequence) {
        RelationRoleAuth relationAuthResource = findBySequence(sequence);
        relationAuthResource.setEnableFlag(EnableFlag.N.getValue());
        relationRoleAuthRepository.flush();
        return relationAuthResource;
    }
}
