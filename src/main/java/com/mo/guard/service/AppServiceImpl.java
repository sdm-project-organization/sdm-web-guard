package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.repository.AppRepository;
import com.mo.guard.service.core.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppRepository appRepository;

    /**
     * findAllByEnableFlag
     *
     * @param enableFlag
     * */
    @Override
    public List<AppEntity> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        return appRepository.findAllByEnableFlag(enableFlag);
    }

    /**
     * findBySequence
     *
     * @param sequence
     * */
    @Override
    public AppEntity findBySequence(int sequence) {
        return appRepository.findBySequence(sequence);
    }

    /**
     * findByDisplayNameAndEnableFlag
     *
     * @param displayName
     * @param enableFlag
     * */
    @Override
    public AppEntity findByDisplayNameAndEnableFlag(String displayName,
                                                    byte enableFlag) {
        if(displayName != null && displayName.equals("") == false) {
            return appRepository.findByDisplayNameAndEnableFlag(displayName, enableFlag);
        }
        return null;
    }

    /**
     * save
     *
     * @param app
     * */
    @Override
    public AppEntity save(AppEntity app) {
        return appRepository.save(app);
    }

    /**
     * updateBySequence
     *
     * @param sequence
     * @param targetApp
     * */
    @Override
    public AppEntity updateBySequence(int sequence,
                                      AppEntity targetApp) {
        AppEntity originApp = findBySequenceAndEnableFlag(sequence, EnableFlag.Y.getValue());
        originApp.setDisplayName(targetApp.getDisplayName());
        originApp.setDisplayOrder(targetApp.getDisplayOrder());
        originApp.setDesc(targetApp.getDesc());
        appRepository.flush();
        return originApp;
    }

    /**
     * unenable
     *
     * @param sequence
     * */
    @Override
    public AppEntity unenable(int sequence) {
        AppEntity app = findBySequence(sequence);
        app.setEnableFlag(EnableFlag.N.getValue());
        appRepository.flush();
        return app;
    }
}