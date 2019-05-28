package com.sdm.login.services;

import com.sdm.login.models.tables.User;
import com.sdm.login.repositorys.UserRepository;
import com.sdm.login.services.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public User findBySequence(int sequence) {
        return null;
    }

    @Override
    public User findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return null;
    }

    @Override
    public User findBySequenceAndActiveFlagAndEnableFlag(int sequence, byte activeFlag, byte enableFlag) {
        return null;
    }

    @Override
    public List<User> findAllByDisplayName(String displayName) {
        return null;
    }

    @Override
    public List<User> findAllByDisplayNameAndEnableFlag(String displayName, byte enableFlag) {
        return null;
    }

    @Override
    public List<User> findAllByDisplayNameAndActiveFlagAndEnableFlag(String displayName, byte activeFlag, byte enableFlag) {
        return null;
    }

    @Override
    public Page<User> findAllByPage(int offset, int limit) {
        return null;
    }

    @Override
    public User save(User user) throws Exception {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> saveAll(List<User> list) {
        return null;
    }

    @Override
    public void updateBySequence(int sequence, User user) throws Exception {

    }

    @Override
    public void active(int sequence) {

    }

    @Override
    public void unactive(int sequence) {

    }

    @Override
    public void enable(int sequence) {

    }

    @Override
    public void unenable(int sequence) throws Exception {

    }

}
