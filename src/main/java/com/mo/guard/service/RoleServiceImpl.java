package com.mo.guard.service;

import com.mo.guard.builder.RequestPathBuilder;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.RoleEntity;
import com.mo.guard.repository.RoleRepository;
import com.mo.guard.service.core.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RequestPathBuilder requestPathBuilder;

    public int countBySequenceIn(List<Integer> listOfRoleId) {
        return roleRepository.countBySequenceIn(listOfRoleId);
    }

    @Override
    public List<RoleEntity> findAllByEnableFlag(EnableFlag enableFlag) {
        return roleRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public RoleEntity findBySequence(int sequence) {
        return roleRepository.findBySequence(sequence);
    }

    @Override
    public RoleEntity findBySequenceAndEnableFlag(int sequence, EnableFlag enableFlag) {
        return roleRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    /**
     * TODO renaming
     * findAllByOrgName - 조직이름으로 Caching ResourceEntity 조회하기
     *
     * @param orgName - 조직이름
     * @return Map - RoleName : Set of ResourceName
     * */
    public Map<String, Object> findAllByOrgName(String orgName) {
        // TODO organ-name 적용예정
        List<RoleEntity> roles = roleRepository.findAllByEnableFlag(EnableFlag.YES);
        Map<String, Object> result = new TreeMap<>();

        roles.stream().forEach(role -> {
            role.getAuths().stream().forEach((auth -> {
                String roleName = role.getDisplayName();
                auth.getResources().stream().forEach(resource -> {
                    String method = resource.getHttpMethod();
                    String path = resource.getHttpPath();
                    requestPathBuilder.build(result, method, path, roleName);
                });
            }));
        });
        return result;
    }

    /**
     * findAllBySequenceIn
     *
     * @param listOfRoleId
     * */
    public List<RoleEntity> findAllBySequenceIn(List<Integer> listOfRoleId) {
        List<RoleEntity> roles = roleRepository.findAllBySequenceIn(listOfRoleId);
        return roles;
    }

    @Override
    public RoleEntity save(RoleEntity obj) {
        return roleRepository.save(obj);
    }

    @Override
    public List<RoleEntity> saveAll(List<RoleEntity> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public RoleEntity updateBySequence(int sequence, RoleEntity targetRole) {
        RoleEntity originRole = findBySequenceAndEnableFlag(sequence, EnableFlag.YES);
        originRole.setExpiredPeriod(targetRole.getExpiredPeriod());
        originRole.setDisplayName(targetRole.getDisplayName());
        originRole.setDisplayOrder(targetRole.getDisplayOrder());
        originRole.setDesc(targetRole.getDesc());
        originRole.setMeta(targetRole.getMeta());
        roleRepository.flush();
        return originRole;
    }

    @Override
    public RoleEntity unenable(int sequence) {
        RoleEntity role = findBySequence(sequence);
        role.setEnableFlag(EnableFlag.NO);
        roleRepository.flush();
        return role;
    }

}
