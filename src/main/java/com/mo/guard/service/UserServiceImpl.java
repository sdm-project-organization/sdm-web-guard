package com.mo.guard.service;


import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.App;
import com.mo.guard.model.table.User;
import com.mo.guard.repository.UserRepository;
import com.mo.guard.service.core.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /*@Autowired
    AuthoritiesServiceImpl authoritiesService;*/

    /**
     * 로그인
     * */
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUsernameAndEnableFlag(username, EnableFlag.Y.getValue());
        return new GuardUserDetails(user, authoritiesService.getAuthorities(user));
    }*/

    /*public UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails;
        }
        throw new UserNotLoginException();
        return null;
    }*/

    @Override
    public List<User> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        return userRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public User findBySequence(int sequence) {
        return userRepository.findBySequence(sequence);
    }

    @Override
    public User findByUsernameAndEnableFlag(String username, byte enableFlag) {
        return userRepository.findByUsernameAndEnableFlag(username, enableFlag);
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
    @Override
    public User unenable(int sequence) {
        User user = findBySequence(sequence);
        user.setEnableFlag(EnableFlag.N.getValue());
        userRepository.flush();
        return user;
    }


}
