package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.exception.NotFoundAppException;
import com.mo.guard.exception.NotFoundOrganException;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.model.entity.OrganEntity;
import com.mo.guard.repository.AppRepository;
import com.mo.guard.repository.OrganRepository;
import com.mo.guard.service.core.AppService;
import com.mo.guard.service.core.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganServiceImpl implements OrganService {

    @Autowired
    OrganRepository organRepository;

    /**
     * findBySequence
     *
     * @param sequence
     */
    @Deprecated
    @Override
    public OrganEntity findBySequence(int sequence) {
        return organRepository.findBySequence(sequence);
    }

    /**
     * findBySequenceAndEnableFlag
     *
     * @param sequence
     * @param enableFlag
     */
    @Override
    public OrganEntity findBySequenceAndEnableFlag(int sequence, EnableFlag enableFlag) {
        return organRepository.findBySequenceAndEnableFlag(sequence, enableFlag);
    }

    /**
     * findByDisplayNameAndEnableFlag
     *
     * @param displayName
     * @param enableFlag
     */
    @Override
    public OrganEntity findByDisplayNameAndEnableFlag(String displayName,
                                                      EnableFlag enableFlag) {
        if (displayName != null && displayName.equals("") == false) {
            return organRepository.findByDisplayNameAndEnableFlag(displayName, enableFlag);
        }
        return null;
    }

    /**
     * findAllByEnableFlag
     *
     * @param enableFlag
     */
    @Override
    public List<OrganEntity> findAllByEnableFlag(EnableFlag enableFlag) {
        return organRepository.findAllByEnableFlag(enableFlag);
    }

    /**
     * save
     *
     * @param organ
     */
    @Override
    public OrganEntity save(OrganEntity organ) {
        return organRepository.save(organ);
    }

    /**
     * updateBySequence
     *
     * @param sequence
     * @param targetOrgan
     */
    @Override
    public OrganEntity updateBySequence(int sequence,
                                        OrganEntity targetOrgan) {
        OrganEntity originOrgan = findBySequenceAndEnableFlag(sequence, EnableFlag.YES);
        if (originOrgan == null)
            return null;
        originOrgan.setDisplayName(targetOrgan.getDisplayName());
        originOrgan.setDisplayOrder(targetOrgan.getDisplayOrder());
        originOrgan.setDesc(targetOrgan.getDesc());
        originOrgan.setActiveFlag(targetOrgan.getActiveFlag());
        /* NO_DELETE - originOrgan.setEnableFlag(targetOrgan.getEnableFlag());*/
        organRepository.flush();
        return originOrgan;
    }

    /**
     * unenable
     *
     * @param sequence
     */
    @Override
    public OrganEntity unenable(int sequence) throws NotFoundOrganException {
        OrganEntity organ = findBySequenceAndEnableFlag(sequence, EnableFlag.YES);
        if (organ == null)
            return null;
        organ.setEnableFlag(EnableFlag.NO);
        organRepository.flush();
        return organ;
    }
}