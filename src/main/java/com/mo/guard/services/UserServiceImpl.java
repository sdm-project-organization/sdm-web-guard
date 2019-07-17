//package com.sdm.login.services;
//
//import EnableFlag;
//import com.sdm.login.models.GuardUserDetails;
//import User;
//import com.sdm.login.repositorys.UserRepository;
//import UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.securitys.core.Authentication;
//import org.springframework.securitys.core.context.SecurityContextHolder;
//import org.springframework.securitys.core.userdetails.UserDetails;
//import org.springframework.securitys.core.userdetails.UserDetailsService;
//import org.springframework.securitys.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService, UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthoritiesServiceImpl authoritiesService;
//
//    /**
//     * 로그인
//     * */
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        User user = this.findByUserIdAndEnableFlag(userId, EnableFlag.Y.getValue());
//        return new GuardUserDetails(user, authoritiesService.getAuthorities(user));
//    }
//
//    public UserDetails getUserDetails() {
//        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            return userDetails;
//        }
//        throw new UserNotLoginException();*/
//        return null;
//    }
//
//    @Override
//    public User findByUserIdAndEnableFlag(String userId, byte enableFlag) {
//        return null;
//    }
//
//    @Override
//    public User save(User user) throws Exception {
//        userRepository.save(user);
//        return user;
//    }
//
//}
