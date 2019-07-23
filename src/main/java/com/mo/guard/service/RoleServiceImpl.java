package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Role;
import com.mo.guard.repository.RoleRepository;
import com.mo.guard.service.core.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAllByEnableFlag(byte enableFlag) {
        /*return roleRepository.findAll();*/
        return roleRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public Role save(Role obj) {
        return roleRepository.save(obj);
    }

    @Override
    public Role findBySequence(int sequence) {
        return roleRepository.findBySequence(sequence);
    }

    @Override
    public Role findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return roleRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
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
