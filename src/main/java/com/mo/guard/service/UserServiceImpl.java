package com.mo.guard.service;


import com.mo.guard.constant.EnableFlag;
import com.mo.guard.exception.NotFoundRoleException;
import com.mo.guard.exception.NotFoundUserException;
import com.mo.guard.model.entity.UserEntity;
import com.mo.guard.repository.UserRepository;
import com.mo.guard.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleService;

    @Override
    public List<UserEntity> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        List<UserEntity> users = userRepository.findAllByEnableFlag(enableFlag);
        return users;
    }

    @Override
    public UserEntity findBySequence(int sequence) {
        UserEntity user = userRepository.findBySequence(sequence);
        return user;
    }

    public UserEntity findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        UserEntity user = userRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
        return user;
    }

    @Override
    public UserEntity findByUsernameAndEnableFlag(String username, byte enableFlag) {
        UserEntity user = userRepository.findByUsernameAndEnableFlag(username, enableFlag);
        return user;
    }

    @Override
    public UserEntity save(UserEntity user) throws RuntimeException {
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateBySequence(int sequence, UserEntity targetUser) {
        UserEntity originUser = findBySequence(sequence);
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

    public UserEntity updateRolesBySequence(int sequence, List<Integer> listOfRoleId) throws RuntimeException {
        UserEntity originUser = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
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
    public UserEntity unenable(int sequence) {
        UserEntity user = findBySequence(sequence);
        user.setEnableFlag(EnableFlag.N.getValue());
        userRepository.flush();
        return user;
    }


}
