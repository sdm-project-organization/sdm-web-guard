package com.mo.guard.service;

import com.mo.guard.builder.RequestPathBuilder;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Role;
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
    public List<Role> findAllByEnableFlag(byte enableFlag) {
        return roleRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public Role findBySequence(int sequence) {
        return roleRepository.findBySequence(sequence);
    }

    @Override
    public Role findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return roleRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    /**
     * TODO renaming
     * findAllByOrgName - 조직이름으로 Caching Resource 조회하기
     *
     * @param orgName - 조직이름
     * @return Map - RoleName : Set of ResourceName
     * */
    public Map<String, Object> findAllByOrgName(String orgName) {
        // TODO organ-name 적용예정
        List<Role> roles = roleRepository.findAllByEnableFlag(EnableFlag.Y.getValue());
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
    public List<Role> findAllBySequenceIn(List<Integer> listOfRoleId) {
        List<Role> roles = roleRepository.findAllBySequenceIn(listOfRoleId);
        return roles;
    }

    @Override
    public Role save(Role obj) {
        return roleRepository.save(obj);
    }

    @Override
    public Role updateBySequence(int sequence, Role targetRole) {
        Role originRole = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originRole.setExpiredPeriod(targetRole.getExpiredPeriod());
        originRole.setDisplayName(targetRole.getDisplayName());
        originRole.setDisplayOrder(targetRole.getDisplayOrder());
        originRole.setDesc(targetRole.getDesc());
        originRole.setMeta(targetRole.getMeta());
        roleRepository.flush();
        return originRole;
    }

    @Override
    public Role unenable(int sequence) {
        Role role = findBySequence(sequence);
        role.setEnableFlag(EnableFlag.N.getValue());
        roleRepository.flush();
        return role;
    }
}
