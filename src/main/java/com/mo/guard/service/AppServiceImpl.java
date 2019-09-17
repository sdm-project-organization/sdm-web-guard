package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.exception.NotFoundAppException;
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
     * findBySequence
     *
     * @param sequence
     * */
    @Deprecated
    @Override
    public AppEntity findBySequence(int sequence) {
        return appRepository.findBySequence(sequence);
    }

    /**
     * findBySequenceAndEnableFlag
     *
     * @param sequence
     * @param enableFlag
     * */
    @Override
    public AppEntity findBySequenceAndEnableFlag(int sequence, EnableFlag enableFlag) {
        return appRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    /**
     * findByDisplayNameAndEnableFlag
     *
     * @param displayName
     * @param enableFlag
     * */
    @Override
    public AppEntity findByDisplayNameAndEnableFlag(String displayName,
                                                    EnableFlag enableFlag) {
        if(displayName != null && displayName.equals("") == false) {
            return appRepository.findByDisplayNameAndEnableFlag(displayName, enableFlag);
        }
        return null;
    }

    /**
     * findAllByEnableFlag
     *
     * @param enableFlag
     * */
    @Override
    public List<AppEntity> findAllByEnableFlag(EnableFlag enableFlag) {
        return appRepository.findAllByEnableFlag(enableFlag);
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
        AppEntity originApp = findBySequenceAndEnableFlag(sequence, EnableFlag.YES);
        if(originApp == null)
            return null;
        originApp.setOrganSequence(targetApp.getOrganSequence());
        originApp.setDisplayName(targetApp.getDisplayName());
        originApp.setDisplayOrder(targetApp.getDisplayOrder());
        originApp.setDesc(targetApp.getDesc());
        originApp.setActiveFlag(targetApp.getActiveFlag());
        /* NO_DELETE - originApp.setEnableFlag(targetApp.getEnableFlag());*/
        appRepository.flush();
        return originApp;
    }

    /**
     * unenable
     *
     * @param sequence
     * */
    @Override
    public AppEntity unenable(int sequence) throws NotFoundAppException {
        AppEntity app = findBySequenceAndEnableFlag(sequence, EnableFlag.YES);
        if(app == null)
            return null;
        app.setEnableFlag(EnableFlag.NO);
        appRepository.flush();
        return app;
    }
}