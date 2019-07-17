//package com.sdm.login.models;
//
//import User;
//import lombok.Data;
//import org.springframework.securitys.core.GrantedAuthority;
//import org.springframework.securitys.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//@Data
//public class GuardUserDetails implements UserDetails {
//
//    private final User user;
//    private final Collection<GrantedAuthority> authorities;
//
//    public GuardUserDetails(User user, Collection<GrantedAuthority> authorities) {
//        this.user = user;
//        this.authorities = authorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getName();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return user.getEnableFlag() == 1 ? true : false;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//
//}
