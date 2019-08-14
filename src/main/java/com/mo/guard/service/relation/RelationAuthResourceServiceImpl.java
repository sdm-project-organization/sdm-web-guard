package com.mo.guard.service.relation;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.relation.RelationAuthResource;
import com.mo.guard.repository.relation.RelationAuthResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class RelationAuthResourceServiceImpl implements RelationAuthResourceService {

    @Autowired
    RelationAuthResourceRepository relationAuthResourceRepository;

    @Override
    public List<RelationAuthResource> findAllByEnableFlag(byte enableFlag) {
        return relationAuthResourceRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public RelationAuthResource save(RelationAuthResource obj) {
        return relationAuthResourceRepository.save(obj);
    }

    @Override
    public RelationAuthResource findByPkAndEnableFlag(String id, byte enableFlag) {
        /*RelationAuthResourceId relationAuthResourceId = new RelationAuthResourceId(
                parseInt(id.split("-")[0]),
                parseInt(id.split("-")[1]));
        return relationAuthResourceRepository.findByKeyAndEnableFlag(relationAuthResourceId, enableFlag);*/
        return null;
    }

    @Override
    public RelationAuthResource updateBySequence(int sequence, RelationAuthResource targetRelationAuthResource) {
        RelationAuthResource originRelationAuthResource = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originRelationAuthResource.setDisplayName(targetRelationAuthResource.getDisplayName());
        originRelationAuthResource.setDisplayOrder(targetRelationAuthResource.getDisplayOrder());
        originRelationAuthResource.setDesc(targetRelationAuthResource.getDesc());
        relationAuthResourceRepository.flush();
        return originRelationAuthResource;
    }

    @Override
    public RelationAuthResource unenable(int sequence) {
        RelationAuthResource relationAuthResource = findBySequence(sequence);
        relationAuthResource.setEnableFlag(EnableFlag.N.getValue());
        relationAuthResourceRepository.flush();
        return relationAuthResource;
    }
}
