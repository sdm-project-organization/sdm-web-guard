package com.mo.guard.service;

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
     * findAllByOrganizationName 조직이름으로 조회하기
     *
     * @param organizationName - 조직이름
     * @return Map - RoleName : Set of ResourceName
     * */
    public Map<String, Set<String>> findAllByOrganizationName(String organizationName) {
        Map<String, Set<String>> result = new HashMap<>();
        List<Role> roles = roleRepository.findAllByEnableFlag(EnableFlag.Y.getValue());
        roles.stream().forEach(role -> {
            Set<String> setOfResource = new HashSet<>();
            role.getAuths().stream().forEach((auth -> {
                auth.getResources().stream().forEach(resource -> {
                    setOfResource.add(resource.getPath());
                });
            }));
            result.put(role.getDisplayName(), setOfResource);
        });
        return result;
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
