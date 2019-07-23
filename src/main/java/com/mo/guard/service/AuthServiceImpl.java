package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Auth;
import com.mo.guard.repository.AuthRepository;
import com.mo.guard.service.core.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthRepository authRepository;

    @Override
    public List<Auth> findAllByEnableFlag(byte enableFlag) {
        /*return authRepository.findAll();*/
        return authRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public Auth save(Auth obj) {
        return authRepository.save(obj);
    }

    @Override
    public Auth findBySequence(int sequence) {
        return authRepository.findBySequence(sequence);
    }

    @Override
    public Auth findBySequenceAndEnableFlag(int sequence, byte enableFlag) {
        return authRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    @Override
    public Auth updateBySequence(int sequence, Auth targetAuth) {
        Auth originAuth = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originAuth.setDisplayName(targetAuth.getDisplayName());
        originAuth.setDisplayOrder(targetAuth.getDisplayOrder());
        originAuth.setDesc(targetAuth.getDesc());
        authRepository.flush();
        return originAuth;
    }

    @Override
    public Auth unenable(int sequence) {
        Auth auth = findBySequence(sequence);
        auth.setEnableFlag(EnableFlag.N.getValue());
        authRepository.flush();
        return auth;
    }
}
