package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.App;
import com.mo.guard.repository.AppRepository;
import com.mo.guard.service.core.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppRepository appRepository;

    @Override
    public List<App> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        return appRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public App save(App obj) {
        return appRepository.save(obj);
    }

    @Override
    public App findBySequence(int sequence) {
        return appRepository.findBySequence(sequence);
    }

    @Override
    public App updateBySequence(int sequence, App targetApp) {
        App originApp = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originApp.setDisplayName(targetApp.getDisplayName());
        originApp.setDisplayOrder(targetApp.getDisplayOrder());
        originApp.setDesc(targetApp.getDesc());
        appRepository.flush();
        return originApp;
    }

    @Override
    public App unenable(int sequence) {
        App app = findBySequence(sequence);
        app.setEnableFlag(EnableFlag.N.getValue());
        appRepository.flush();
        return app;
    }
}
