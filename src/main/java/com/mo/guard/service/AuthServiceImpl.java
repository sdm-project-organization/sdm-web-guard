package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.AuthEntity;
import com.mo.guard.model.entity.relation.RelationAuthResource;
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
    public List<AuthEntity> findAllByEnableFlag(byte enableFlag) {
        return authRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public AuthEntity save(AuthEntity obj) throws RuntimeException {
        return authRepository.save(obj);
    }

    @Override
    public List<AuthEntity> saveAll(List<AuthEntity> auths) throws RuntimeException {
        return authRepository.saveAll(auths);
    }

    @Override
    public AuthEntity findBySequence(int sequence) {
        return authRepository.findBySequence(sequence);
    }

    @Override
    public AuthEntity findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return authRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    @Override
    public AuthEntity updateBySequence(int sequence, AuthEntity targetAuth) {
        AuthEntity originAuth = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originAuth.setDisplayName(targetAuth.getDisplayName());
        originAuth.setDisplayOrder(targetAuth.getDisplayOrder());
        originAuth.setDesc(targetAuth.getDesc());
        authRepository.flush();
        return originAuth;
    }

    /**
     * updateRelationWithResourcesBySequence - `AuthEntity` 와 `ResourceEntity` 간의 관계 관리
     *
     * @param authSequence - AuthEntity Id
     * @param listOfNewResourceId - 새로운 ResourceEntity Id 목록
     * */
    public void updateRelationWithResourcesBySequence(int authSequence, List<Integer> listOfNewResourceId) throws Exception {
        AuthEntity auth = authRepository.findBySequenceAndEnableFlag(authSequence, EnableFlag.Y.getValue());
        List<Integer> listOfOriginResourceId;
        List<Integer> listOfNoDuplicate = listOfNewResourceId;
        List<RelationAuthResource> result = new ArrayList<>();

        if(auth == null) {
            throw new Exception("not found auth");
        }

        // remove & select
        if(auth.getResources().size() > 0) {
            listOfOriginResourceId = auth.getResources().stream().filter(((originResource)->{
                if(listOfNewResourceId.indexOf(originResource.getSequence()) == -1) {
                    originResource.setEnableFlag(EnableFlag.N.getValue()); // remove
                    return false; // non-select
                } else {
                    return true; // select
                }
            })).mapToInt((resource)-> resource.getSequence()    )
                    .boxed().collect(Collectors.toList());

            // Validate
            listOfNoDuplicate = ArrayUtil.findNoDuplicateInArrays(
                    listOfNewResourceId/*create+stay-id*/, listOfOriginResourceId/*stay-id*/);
        }

        // Insert
        if(listOfNoDuplicate.size() > 0) {
            listOfNoDuplicate.forEach((resourceSequence) -> {
                RelationAuthResource relationAuthResource = new RelationAuthResource();
                relationAuthResource.setAuthSequence(authSequence);
                relationAuthResource.setResourceSequence(resourceSequence);
                result.add(relationAuthResource);
            });
            relationAuthResourceRepository.saveAll(result);
        }

        relationAuthResourceRepository.flush();
    }

    @Override
    public AuthEntity unenable(int sequence) {
        AuthEntity auth = findBySequence(sequence);
        auth.setEnableFlag(EnableFlag.N.getValue());
        authRepository.flush();
        return auth;
    }
}
