//package com.sdm.login.services;
//
//import com.sdm.login.models.tables.User;
//import org.springframework.securitys.core.GrantedAuthority;
//import org.springframework.securitys.core.authority.AuthorityUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
//@Service
//public class AuthoritiesServiceImpl {
//
//    public Collection<GrantedAuthority> getAuthorities(User user) {
//        /*switch (user.getAuthority()) {
//            case "admin": *//*messageUtil.getMessage("user.authority.admin")*//*
//                return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_EMPLOYEE", "ROLE_ADMIN");
//            case "employee": *//*messageUtil.getMessage("user.authority.employee")*//*
//                return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_EMPLOYEE");
//            default: *//*messageUtil.getMessage("user.authority.user")*//*
//                return AuthorityUtils.createAuthorityList("ROLE_USER");
//        }*/
//        return null;
//    }
//}