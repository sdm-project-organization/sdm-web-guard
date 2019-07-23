package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Auth;
import com.mo.guard.model.table.relation.RelationAuthResource;
import com.mo.guard.repository.AuthRepository;
import com.mo.guard.repository.relation.RelationAuthResourceRepository;
import com.mo.guard.service.core.AuthService;
import com.mo.guard.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RelationAuthResourceRepository relationAuthResourceRepository;

    @Override
    public List<Auth> findAllByEnableFlag(byte enableFlag) {
        /*return authRepository.findAll();*/
        return authRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public Auth save(Auth obj) {
        return authRepository.save(obj);
    }

    @Override
    public Auth findBySequence(int sequence) {
        return authRepository.findBySequence(sequence);
    }

    @Override
    public Auth findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return authRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    @Override
    public Auth updateBySequence(int sequence, Auth targetAuth) {
        Auth originAuth = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originAuth.setDisplayName(targetAuth.getDisplayName());
        originAuth.setDisplayOrder(targetAuth.getDisplayOrder());
        originAuth.setDesc(targetAuth.getDesc());
        authRepository.flush();
        return originAuth;
    }

    /**
     * [DB] In Query vs [WEB] Server Logic
     *
     * [origin] 1, 2, 3 / [target] 2, 4
     * delete 1, 3
     * create 4
     * stayon 2
     *
     * */
    /*@Override*/
    public void updateResourcesBySequence(int authSequence, List<Integer> listOfNewResourceId) throws Exception {

        List<RelationAuthResource> listOfRelationAuthResource = relationAuthResourceRepository
                .findByAuthSequenceAndEnableFlag(authSequence, EnableFlag.Y.getValue());

        // remove & select
        List<Integer> listOfOriginResourceId = listOfRelationAuthResource.stream().filter(((resource)->{
            if(listOfNewResourceId.indexOf(resource.getResourceSequence()) == -1) {
                resource.setEnableFlag(EnableFlag.N.getValue());
                return false;
            } else {
                return true;
            }
        })).mapToInt((resource)->{
            return resource.getResourceSequence();
        }).boxed().collect(Collectors.toList());

        // Validate
        List<Integer> listOfNoDuplicate = ArrayUtil.findNoDuplicateInArrays(
                listOfNewResourceId,
                listOfOriginResourceId);

        // Intert
        List<RelationAuthResource> result = new ArrayList<>();
        listOfNoDuplicate.forEach((a) -> {
            RelationAuthResource relationAuthResource = new RelationAuthResource();
            relationAuthResource.setAuthSequence(authSequence);
            relationAuthResource.setResourceSequence(a);
            relationAuthResourceRepository.saveAndFlush(relationAuthResource);
            result.add(relationAuthResource);
        });

        /*relationAuthResourceRepository.saveAll(result);*/
        relationAuthResourceRepository.flush();
    }

    @Override
    public Auth unenable(int sequence) {
        Auth auth = findBySequence(sequence);
        auth.setEnableFlag(EnableFlag.N.getValue());
        authRepository.flush();
        return auth;
    }
}
