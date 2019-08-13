package com.mo.guard.service;


import com.mo.guard.constant.EnableFlag;
import com.mo.guard.exception.NotFoundRoleException;
import com.mo.guard.exception.NotFoundUserException;
import com.mo.guard.model.table.App;
import com.mo.guard.model.table.Role;
import com.mo.guard.model.table.User;
import com.mo.guard.repository.RoleRepository;
import com.mo.guard.repository.UserRepository;
import com.mo.guard.service.core.RoleService;
import com.mo.guard.service.core.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleService;

    @Override
    public List<User> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        List<User> users = userRepository.findAllByEnableFlag(enableFlag);
        return users;
    }

    @Override
    public User findBySequence(int sequence) {
        User user = userRepository.findBySequence(sequence);
        return user;
    }

    public User findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        User user = userRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
        return user;
    }

    @Override
    public User findByUsernameAndEnableFlag(String username, byte enableFlag) {
        User user = userRepository.findByUsernameAndEnableFlag(username, enableFlag);
        return user;
    }

    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User updateBySequence(int sequence, User targetUser) {
        User originUser = findBySequence(sequence);
        originUser.setUsername(targetUser.getUsername());
        originUser.setPassword(targetUser.getPassword());
        originUser.setName(targetUser.getName());
        originUser.setEmail(targetUser.getEmail());
        originUser.setDisplayName(targetUser.getDisplayName());
        originUser.setDisplayOrder(targetUser.getDisplayOrder());
        originUser.setDesc(targetUser.getDesc());

        userRepository.flush();
        return originUser;
    }

    public User updateRolesBySequence(int sequence, List<Integer> listOfRoleId) throws RuntimeException {
        User originUser = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        if(originUser == null)
            throw new NotFoundUserException();

        // validation
        if(listOfRoleId.size() != 0) {
            // TODO 비효율적...
            int countOfRole = roleService.countBySequenceIn(listOfRoleId);
            if(listOfRoleId.size() != countOfRole)
                throw new NotFoundRoleException();
            originUser.setRoles(roleService.findAllBySequenceIn(listOfRoleId));
        } else {
            originUser.getRoles().clear();
        }

        // TODO relation table 필수조건 어떻게 추가 ?
        // TODO relation delete 이슈 -> 논리삭제필요
        userRepository.flush();
        return originUser;
    }

    @Override
    public User unenable(int sequence) {
        User user = findBySequence(sequence);
        user.setEnableFlag(EnableFlag.N.getValue());
        userRepository.flush();
        return user;
    }


}
